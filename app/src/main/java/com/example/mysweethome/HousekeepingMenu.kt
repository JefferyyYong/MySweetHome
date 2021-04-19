package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class HousekeepingMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.housekeeping_menu)

        val adminName = findViewById<TextView>(R.id.adminName)
        setTitle("Housekeeping");
        adminName.setText("Christal")


        //Admin

        val toTask = findViewById<ImageView>(R.id.imgViewTaskAllocation)
        val toInspect = findViewById<ImageView>(R.id.imgViewInspection)
        val toLogout = findViewById<Button>(R.id.btnLogout)


        toTask.setOnClickListener {
            val intent = Intent(this, AdminTask::class.java)
            startActivity(intent)
        }

        toInspect.setOnClickListener {
            val intent = Intent(this, InspectMenu::class.java)
            startActivity(intent)
        }

        toLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}