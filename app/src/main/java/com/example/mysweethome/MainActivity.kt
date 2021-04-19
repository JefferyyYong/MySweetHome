package com.example.mysweethome


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginBtn = findViewById<Button>(R.id.Login)
        val signUpBtn = findViewById<Button>(R.id.Signup)

        loginBtn.setOnClickListener{
            //val intent = Intent(this, Login::class.java)
            intent = Intent(this, InspectRoomList::class.java)
            //val intent = Intent(this, FrontDesk::class.java)
            startActivity(intent)
        }

        signUpBtn.setOnClickListener{
            val intent = Intent(this, signUp::class.java)
            startActivity(intent)
        }
    }
}
