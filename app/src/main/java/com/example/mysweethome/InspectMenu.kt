package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView

class InspectMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inspect_menu)

        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Inspection"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val toInspectRoom = findViewById<ImageView>(R.id.imgViewInspectRoom)
        val toInspectFloor = findViewById<ImageView>(R.id.imgViewInspectFloor)

        toInspectRoom.setOnClickListener {
            val intent = Intent(this, InspectRoom::class.java)
            startActivity(intent)
        }

        toInspectFloor.setOnClickListener {
            val intent = Intent(this, InspectFloor::class.java)
            startActivity(intent)
        }
    }

    //Side menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Item1 -> {
                val intent = Intent(this, FrontDesk::class.java)
                startActivity(intent)
                return true
            }
            R.id.Item2 ->{
                val intent = Intent(this, HousekeepingMenu::class.java)
                startActivity(intent)
                return true
            }
            R.id.Item3 ->{
                val intent = Intent(this, AdminTask::class.java)
                startActivity(intent)
                return true
            }
            R.id.Item4 ->{
                val intent = Intent(this, InspectMenu::class.java)
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