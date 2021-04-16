package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CustPayment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cust_payment)

        val btnPay= findViewById<Button>(R.id.btnPay)
        val cancelBtn = findViewById<Button>(R.id.btnCancelPay)

        btnPay.setOnClickListener {
            val intent = Intent(this, ReservationHistory::class.java)
            startActivity(intent)
        }

        cancelBtn.setOnClickListener {
            val intent = Intent(this, CustMenu::class.java)
            startActivity(intent)
        }
    }
}