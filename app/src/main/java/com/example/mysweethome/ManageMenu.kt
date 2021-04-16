package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ManageMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manage_menu)

        val changeBtn = findViewById<Button>(R.id.changeBtn)

        changeBtn.setOnClickListener {
            val intent = Intent(this, ManageMenu2::class.java)
            startActivity(intent)
        }
    }
}