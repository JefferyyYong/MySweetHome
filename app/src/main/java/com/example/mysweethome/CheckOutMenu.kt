package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CheckOutMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_out_menu)

        val yes = findViewById<Button>(R.id.selectBtn1)
        val no = findViewById<Button>(R.id.selectBtn2)

        yes.setOnClickListener {
            val intent = Intent(this, CheckOutMenu2::class.java)
            startActivity(intent)
        }

        no.setOnClickListener {
            val intent = Intent(this, FrontDesk::class.java)
            startActivity(intent)
        }
    }
}