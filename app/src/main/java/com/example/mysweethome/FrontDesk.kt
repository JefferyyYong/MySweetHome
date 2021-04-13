package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class FrontDesk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.front_desk)

        val checkInMenuBtn = findViewById<ImageButton>(R.id.checkInBtn)
        val checkOutMenuBtn = findViewById<ImageButton>(R.id.checkOutBtn)
        val manageMenuBtn = findViewById<ImageButton>(R.id.manageRoomBtn)
        val roomStatusBtn = findViewById<ImageButton>(R.id.roomStatusBtn)
        val logOutBtn = findViewById<Button>(R.id.logOutBtn)

        checkInMenuBtn.setOnClickListener {
            val intent = Intent(this, CheckInMenu::class.java)
            startActivity(intent)
        }

        checkOutMenuBtn.setOnClickListener {
            val intent = Intent(this, CheckOutMenu::class.java)
            startActivity(intent)
        }
        manageMenuBtn.setOnClickListener {
            val intent = Intent(this, ManageMenu::class.java)
            startActivity(intent)
        }
        roomStatusBtn.setOnClickListener {
            val intent = Intent(this, RoomStatus::class.java)
            startActivity(intent)
        }
        logOutBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


}