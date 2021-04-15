package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CheckOutMenu2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_out_menu2)

        val proceed = findViewById<Button>(R.id.selectBtn4)
        val cancel = findViewById<Button>(R.id.selectBtn3)

        proceed.setOnClickListener {
            val intent = Intent(this,  FrontDesk::class.java)
            startActivity(intent)
        }

        cancel.setOnClickListener {
            val intent = Intent(this, CheckOutMenu::class.java)
            startActivity(intent)
        }
    }
}