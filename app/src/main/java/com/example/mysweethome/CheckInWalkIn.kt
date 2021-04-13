package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class CheckInWalkIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_in_2)

        val checkWalkInBtn = findViewById<Button>(R.id.walkInNxt)

        checkWalkInBtn.setOnClickListener {
            val intent = Intent(this, CustomerDetails::class.java)
            startActivity(intent)
        }
    }
}