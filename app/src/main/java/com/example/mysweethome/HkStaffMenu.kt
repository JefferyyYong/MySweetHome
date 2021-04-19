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

        val loginName = intent.getStringExtra("HKS1").toString()

        //Staff
        val toLostFound = findViewById<ImageView>(R.id.imgViewLostFound)
        val toTodo = findViewById<ImageView>(R.id.ivTodo)
        val toLogout = findViewById<Button>(R.id.btnLogout)

        toLostFound.setOnClickListener {
            val intent = Intent(this, LostFoundTable::class.java)
            intent.putExtra("HKS1",loginName)
            startActivity(intent)
        }

        toTodo.setOnClickListener{
            val intent = Intent(this, HkStaffTask::class.java)
            intent.putExtra("HKS1",loginName)
            startActivity(intent)
        }

        toLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}