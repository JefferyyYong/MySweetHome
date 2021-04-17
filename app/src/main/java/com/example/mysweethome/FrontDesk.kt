package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class FrontDesk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.front_desk)

        val checkInMenuBtn = findViewById<ImageButton>(R.id.checkInBtn)
        val checkOutMenuBtn = findViewById<ImageButton>(R.id.checkOutBtn)
        val manageMenuBtn = findViewById<ImageButton>(R.id.manageRoomBtn)
        val logOutBtn = findViewById<Button>(R.id.logOutBtn)
        val loginName = findViewById<TextView>(R.id.loginName)
        val room      = findViewById<ImageButton>(R.id.roomBtn)
        setTitle("Front Desk Menu")

        loginName.text = "Jeffery"

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
        logOutBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        room.setOnClickListener {
            val intent = Intent(this, RoomStatus::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Item1 -> {
                val intent = Intent(this, FrontDesk::class.java)
                startActivity(intent)
                return true
            }
            R.id.Item2 ->{
                val intent = Intent(this, CheckInMenu::class.java)
                startActivity(intent)
                return true
            }
            R.id.Item3 ->{
                val intent = Intent(this, CheckOutMenu::class.java)
                startActivity(intent)
                return true
            }
            R.id.Item4 ->{
                val intent = Intent(this, RoomStatus::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}