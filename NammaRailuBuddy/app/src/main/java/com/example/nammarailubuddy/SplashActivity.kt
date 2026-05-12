package com.example.nammarailubuddy

import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔥 THIS LINE WAS MISSING
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, StationSelectionActivity::class.java))
            finish()
        }, 2000)
    }
}