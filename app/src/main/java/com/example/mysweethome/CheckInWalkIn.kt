package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

class CheckInWalkIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_in_2)

        val checkWalkInBtn = findViewById<Button>(R.id.walkInNxt)
        val name = findViewById<EditText>(R.id.customerName)
        val ic = findViewById<EditText>(R.id.customerIc)

        val back = findViewById<Button>(R.id.walkInNxt)
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Check - In"
        actionbar.setDisplayHomeAsUpEnabled(true)


        back.setOnClickListener {
            val intent = Intent(this, CheckInMenu::class.java)
            startActivity(intent)
        }


        checkWalkInBtn.setOnClickListener {
            var customerName: String = name.text.toString()
            var customerIc: String = ic.text.toString()

            val intent = Intent(this, CustomerDetails::class.java)
            intent.putExtra("CustomerName",customerName)
            intent.putExtra("CustomerIC",customerIc)
            startActivity(intent)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}