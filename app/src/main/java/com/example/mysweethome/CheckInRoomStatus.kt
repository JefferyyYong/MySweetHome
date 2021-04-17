package com.example.mysweethome

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class CheckInRoomStatus : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_in_room_status)
        //change color will be better
        val room_no = findViewById<TextView>(R.id.roomNo)
        val room201 = findViewById<Button>(R.id.room201)
        val room202 = findViewById<Button>(R.id.room202)
        val room301 = findViewById<Button>(R.id.room301)
        val room302 = findViewById<Button>(R.id.room302)
        val room401 = findViewById<Button>(R.id.room401)
        val room402 = findViewById<Button>(R.id.room402)
        val room501 = findViewById<Button>(R.id.room501)
        val room502 = findViewById<Button>(R.id.room502)
        val nxt = findViewById<Button>(R.id.nxtBtn)

        setTitle("Check In")

        room201.setOnClickListener {
            room_no.setText("201")
        }
        room202.setOnClickListener {
            room_no.setText("202")
        }
        room301.setOnClickListener {
            room_no.setText("301")
        }
        room302.setOnClickListener {
            room_no.setText("302")
        }
        room401.setOnClickListener {
            room_no.setText("401")
        }
        room402.setOnClickListener {
            room_no.setText("402")
        }
        room501.setOnClickListener {
            room_no.setText("501")
        }
        room502.setOnClickListener {
            room_no.setText("502")
        }

        nxt.setOnClickListener {
            val intent = Intent(this, FrontDesk::class.java)
            startActivity(intent)
        }

    }
}