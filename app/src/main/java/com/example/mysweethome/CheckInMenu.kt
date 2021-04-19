package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton

class CheckInMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_in)

        val walkInType = findViewById<ImageButton>(R.id.walkInBtn)
        val reservType = findViewById<ImageButton>(R.id.reservBtn)

        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Check In"
        actionbar.setDisplayHomeAsUpEnabled(true)

        walkInType.setOnClickListener{
            val intent = Intent(this, CheckInWalkIn::class.java)
            startActivity(intent)
        }

        reservType.setOnClickListener{
            val intent = Intent(this, CheckInReservation::class.java)
            startActivity(intent)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Item1 -> {
                var x = "Jeffery"
                val intent = Intent(this, FrontDesk::class.java)
                intent.putExtra("FrontDeskStaff",x)
                startActivity(intent)
                return true
            }
            R.id.Item2 ->{
                val intent = Intent(this, CheckInMenu::class.java)
                startActivity(intent)
                return true
            }
            R.id.Item3 ->{
                val intent = Intent(this, CheckOutMenu::class.java)
                startActivity(intent)
                return true
            }
            R.id.Item4 ->{
                val intent = Intent(this, RoomStatus::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    //back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}