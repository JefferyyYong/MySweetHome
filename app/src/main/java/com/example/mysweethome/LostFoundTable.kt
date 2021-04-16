package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LostFoundTable : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lost_found_table)

        setTitle("Lost and Found");

        val addBtn= findViewById<Button>(R.id.btnAdd)
        val editBtn= findViewById<Button>(R.id.btnEdit)

        addBtn.setOnClickListener {
            val intent = Intent(this, LostFoundAdd::class.java)
            startActivity(intent)
        }

        editBtn.setOnClickListener {
            val intent = Intent(this, LostFoundEdit::class.java)
            startActivity(intent)
        }
    }
}