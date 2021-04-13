package com.example.mysweethome


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginChip = findViewById<Chip>(R.id.Login)
        val signUpChip = findViewById<Chip>(R.id.Signup)


        loginChip.setOnClickListener{
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        //loginChip.setOnClickListener{
        //    val intent = Intent(this, AdminMenu::class.java)
        //    startActivity(intent)
        //}

        signUpChip.setOnClickListener{
            val intent = Intent(this, signUp::class.java)
            startActivity(intent)
        }
    }
}
