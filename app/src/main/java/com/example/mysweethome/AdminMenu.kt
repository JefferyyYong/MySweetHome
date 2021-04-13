package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AdminMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_menu)

        val test1 = findViewById<ImageView>(R.id.imgViewFrontDesk)

        test1.setOnClickListener {
            val intent = Intent(this, FrontDesk::class.java)
            startActivity(intent)
        }
    }
}