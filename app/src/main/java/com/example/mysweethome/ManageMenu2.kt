package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ManageMenu2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manage_menu2)

        val update = findViewById<Button>(R.id.updateBtn)

        update.setOnClickListener {
            val intent = Intent(this, FrontDesk::class.java)
            startActivity(intent)
        }
    }
}