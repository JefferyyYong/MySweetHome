package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class InspectMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inspect_menu)

        setTitle("Inspection");

        val toInspectRoom = findViewById<ImageView>(R.id.imgViewInspectRoom)
        val toInspectFloor = findViewById<ImageView>(R.id.imgViewInspectFloor)

        toInspectRoom.setOnClickListener {
            val intent = Intent(this, InspectRoom::class.java)
            startActivity(intent)
        }

        toInspectFloor.setOnClickListener {
            val intent = Intent(this, InspectFloor::class.java)
            startActivity(intent)
        }
    }
}