package com.example.mysweethome

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class CustMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_menu)

        //Toolbar
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        var title = findViewById<TextView>(R.id.tvTittle)
        title.text = "Customer Menu"


        val custReservationBtn = findViewById<ImageView>(R.id.imgMakeReservation)
        val imgViewHistoryBtn = findViewById<ImageView>(R.id.imgViewHistory)
        //val homeBtn = findViewById<ImageView>(R.id.ivHomepage)
        val custName = findViewById<TextView>(R.id.tvUsername)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val userEmail = user.email
            if(userEmail == "123@gmail.com"){
                custName.text = "Peter"
            }else if (userEmail == "456@gmail.com"){
                custName.text = "James"
            }else{
                custName.text ="Lily"
            }
        } else {
            // No user is signed in
        }

        btnLogout.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        custReservationBtn.setOnClickListener {
            //var x = intent.getStringExtra("CustomerEmail").toString()
            val intent = Intent(this, CustReservation::class.java)
            //intent.putExtra("CustomerEmail",x) //test
            startActivity(intent)
        }

        imgViewHistoryBtn.setOnClickListener {
            val intent = Intent(this, ReservationHistory::class.java)
            startActivity(intent)
        }
    }
}