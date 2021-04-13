package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class HkStaffMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.housekeeping_staff_menu)

        //Staff

        val toLostFound = findViewById<ImageView>(R.id.imgViewLostFound)

        toLostFound.setOnClickListener {
            val intent = Intent(this, AdminTask::class.java)
            startActivity(intent)
        }
    }
}