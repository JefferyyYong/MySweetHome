package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class HkStaffMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.housekeeping_staff_menu)
        val staffName = findViewById<TextView>(R.id.staffName)
        setTitle("Staff Menu");
        staffName.setText(intent.getStringExtra("HKS1").toString())
        //Staff

        val toLostFound = findViewById<ImageView>(R.id.imgViewLostFound)
        val toLogout = findViewById<Button>(R.id.btnLogout)

        toLostFound.setOnClickListener {
            val intent = Intent(this, LostFoundTable::class.java)
            startActivity(intent)
        }

        toLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}