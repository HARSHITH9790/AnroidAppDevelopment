package com.example.nammarailubuddy

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.*

class TrainDetailActivity : AppCompatActivity() {

    lateinit var db: DatabaseReference
    lateinit var platformText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train_detail)

        db = FirebaseDatabase.getInstance().reference

        val routeText = findViewById<TextView>(R.id.routeText)
        val trainName = findViewById<TextView>(R.id.trainNameText)

        platformText = findViewById(R.id.platformText)

        val button = findViewById<Button>(R.id.platformBtn)
        val alarmBtn = findViewById<Button>(R.id.alarmBtn)

        val from = intent.getStringExtra("from") ?: "Unknown"
        val to = intent.getStringExtra("to") ?: "Unknown"

        routeText.text = "$from → $to"

        val train = intent.getStringExtra("train") ?: "Unknown Train"
        trainName.text = train

        val platformInfo =
            intent.getStringExtra("platform")
                ?: "Platform info unavailable"

        platformText.text =
            "$platformInfo\nConfirmed by 7 users"

        listenForUpdates()

        button.setOnClickListener {
            pingPlatform()
        }

        alarmBtn.setOnClickListener {
            checkDistance()
        }
    }

    fun checkDistance() {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )

            return
        }

        val fusedLocation =
            LocationServices.getFusedLocationProviderClient(this)

        fusedLocation.lastLocation
            .addOnSuccessListener { location ->

                if (location != null) {

                    val distance = FloatArray(1)

                    val to =
                        intent.getStringExtra("to")

                    var destLat = 12.2958
                    var destLng = 76.6394

                    if (to == "KSR Bengaluru") {

                        destLat = 12.9716
                        destLng = 77.5946

                    } else if (to == "Mysuru") {

                        destLat = 12.2958
                        destLng = 76.6394

                    } else if (to == "Mandya") {

                        destLat = 12.5223
                        destLng = 76.8975
                    }

                    Location.distanceBetween(
                        location.latitude,
                        location.longitude,
                        destLat,
                        destLng,
                        distance
                    )

                    if (distance[0] < 15000) {

                        Toast.makeText(
                            this,
                            "You are near destination!",
                            Toast.LENGTH_LONG
                        ).show()

                    } else {

                        Toast.makeText(
                            this,
                            "You are far from destination",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } else {

                    Toast.makeText(
                        this,
                        "Location not found",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun pingPlatform() {

        val ref =
            db.child("platforms")
                .child("train1")
                .child("platform2")

        ref.get().addOnSuccessListener {

            val current =
                it.getValue(Int::class.java) ?: 0

            ref.setValue(current + 1)
        }
    }

    fun listenForUpdates() {

        val ref =
            db.child("platforms")
                .child("train1")
                .child("platform2")

        ref.addValueEventListener(
            object : ValueEventListener {

                override fun onDataChange(
                    snapshot: DataSnapshot
                ) {

                    val count =
                        snapshot.getValue(Int::class.java) ?: 0

                    val currentText =
                        intent.getStringExtra("platform")
                            ?: "Platform info unavailable"

                    platformText.text =
                        "$currentText\nConfirmed by $count users"
                }

                override fun onCancelled(
                    error: DatabaseError
                ) {
                }
            })
    }
}