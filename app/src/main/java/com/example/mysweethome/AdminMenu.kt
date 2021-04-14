package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class AdminMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_menu)

        setTitle("Admin Menu");

        val toFrontDesk = findViewById<ImageView>(R.id.imgViewFrontDesk)
        val toViewHousekeeping = findViewById<ImageView>(R.id.imgViewHousekeeping)
        val toLogout = findViewById<Button>(R.id.btnLogout)

        toFrontDesk.setOnClickListener {
            val intent = Intent(this, FrontDesk::class.java)
            startActivity(intent)
        }

        toViewHousekeeping.setOnClickListener {
            val intent = Intent(this, HousekeepingMenu::class.java)
            startActivity(intent)
        }

        toLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}