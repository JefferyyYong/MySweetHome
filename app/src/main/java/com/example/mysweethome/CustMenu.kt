package com.example.mysweethome

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class CustMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_menu)
        setTitle("Customer Menu")

        val custReservationBtn = findViewById<ImageView>(R.id.imgMakeReservation)

        custReservationBtn.setOnClickListener {
            val intent = Intent(this, CustReservation::class.java)
            startActivity(intent)
        }
    }
}