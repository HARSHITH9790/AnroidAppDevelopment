package com.example.nammarailubuddy

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class StationSelectionActivity : AppCompatActivity() {

    lateinit var fromSpinner: Spinner
    lateinit var toSpinner: Spinner
    lateinit var button: Button

    val stations = listOf(
        "Mandya",
        "Mysuru",
        "KSR Bengaluru",
        "Birur"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_selection)

        fromSpinner = findViewById(R.id.fromStation)
        toSpinner = findViewById(R.id.toStation)
        button = findViewById(R.id.findTrainsBtn)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            stations
        )

        fromSpinner.adapter = adapter
        toSpinner.adapter = adapter

        button.setOnClickListener {

            val from = fromSpinner.selectedItem.toString()
            val to = toSpinner.selectedItem.toString()

            // 🚀 Move to next screen (we'll build it next)
            val intent = Intent(this, TrainListActivity::class.java)
            intent.putExtra("from", from)
            intent.putExtra("to", to)
            startActivity(intent)
        }
    }
}