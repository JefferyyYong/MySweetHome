package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class CheckOutMenu2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_out_menu2)

        val proceed = findViewById<Button>(R.id.selectBtn4)
        val cancel = findViewById<Button>(R.id.selectBtn3)
        val name = findViewById<TextView>(R.id.cName)

        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Check Out"
        actionbar.setDisplayHomeAsUpEnabled(true)

        name.setText(intent.getStringExtra("CustomerName").toString())

        val customerName = intent.getStringExtra("CustomerName").toString()
        val customerIc = intent.getStringExtra("CustomerIC").toString()


        proceed.setOnClickListener {
            deleteData()
            val intent = Intent(this,  FrontDesk::class.java)
            startActivity(intent)
        }

        cancel.setOnClickListener {
            val intent = Intent(this, CheckOutMenu::class.java)
            startActivity(intent)
        }
    }

    private fun deleteData() {
        val ref = FirebaseDatabase.getInstance().getReference().child("reservation_table").child(intent.getStringExtra("CustomerIc").toString())
        ref.removeValue()
        Toast.makeText(applicationContext,"Check Out Successfully!",Toast.LENGTH_LONG).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}