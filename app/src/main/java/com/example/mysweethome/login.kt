package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val loginButton = findViewById<Button>(R.id.loginBtn)

        loginButton.setOnClickListener {
            val intent = Intent(this, FrontDesk::class.java)
            startActivity(intent)
        }

        //loginButton.setOnClickListener {
        //    val intent = Intent(this, AdminMenu::class.java)
        //    startActivity(intent)
        //}
    }
}