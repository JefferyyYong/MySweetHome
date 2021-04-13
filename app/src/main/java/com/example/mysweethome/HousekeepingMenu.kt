package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class HousekeepingMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.housekeeping_menu)

        //Admin

        val toTask = findViewById<ImageView>(R.id.imgViewTaskAllocation)
        val toInspect = findViewById<ImageView>(R.id.imgViewInspection)

        toTask.setOnClickListener {
            val intent = Intent(this, AdminTask::class.java)
            startActivity(intent)
        }

        toInspect.setOnClickListener {
            val intent = Intent(this, InspectMenu::class.java)
            startActivity(intent)
        }
    }
}