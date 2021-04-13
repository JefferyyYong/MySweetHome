package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class CheckInMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_in)

        val walkInType = findViewById<ImageButton>(R.id.walkInBtn)
        val reservType = findViewById<ImageButton>(R.id.reservBtn)

        walkInType.setOnClickListener{
            val intent = Intent(this, CheckInWalkIn::class.java)
            startActivity(intent)
        }

        reservType.setOnClickListener{
            val intent = Intent(this, CheckInWalkIn::class.java)
            startActivity(intent)
        }

    }
}