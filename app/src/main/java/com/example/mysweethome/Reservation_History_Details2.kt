package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar

class Reservation_History_Details2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation__history__details2)
        //ToolBars
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        var title = findViewById<TextView>(R.id.tvTittle)
        title.text = "Reservation History"

        val name = findViewById<TextView>(R.id.nameR)
        val no = findViewById<TextView>(R.id.phoneNo)
        val type = findViewById<TextView>(R.id.type3)
        val pax = findViewById<TextView>(R.id.pax4)
        val cid = findViewById<TextView>(R.id.cid2)
        val cod = findViewById<TextView>(R.id.cod2)
        val amount = findViewById<TextView>(R.id.amount)
        val btn = findViewById<Button>(R.id.doneBtn)

        name.setText(" "+intent.getStringExtra("Name").toString())
        no.setText(" "+intent.getStringExtra("No").toString())
        type.setText(" "+intent.getStringExtra("Type").toString())
        pax.setText(" "+intent.getStringExtra("Pax").toString())
        cid.setText(" "+intent.getStringExtra("In").toString())
        cod.setText(" "+intent.getStringExtra("Out").toString())
        amount.setText(" "+intent.getStringExtra("Amount").toString())

        btn.setOnClickListener {
            val intent = Intent(this, CustMenu::class.java)
            startActivity(intent)
        }
    }

    //back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}