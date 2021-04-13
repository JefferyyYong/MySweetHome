package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Reservation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reservation_list)

        val reservSearch = findViewById<Button>(R.id.searchReservBtn)

        reservSearch.setOnClickListener {
            val intent = Intent(this, CheckInMenu::class.java)
            startActivity(intent)
        }

    }
}