package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar

class AdminMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_menu)

        val adminName = findViewById<TextView>(R.id.adminName)
        setTitle("Admin Menu");
        adminName.setText("Christal")

        val toFrontDesk = findViewById<ImageView>(R.id.imgViewFrontDesk)
        val toViewHousekeeping = findViewById<ImageView>(R.id.imgViewHousekeeping)
        val toLogout = findViewById<Button>(R.id.btnLogout)

        toFrontDesk.setOnClickListener {
            var x = "Christal"
            val intent = Intent(this, FrontDesk::class.java)
            intent.putExtra("FrontDeskStaff",x)
            startActivity(intent)
        }

        toViewHousekeeping.setOnClickListener {
            var x = "Christal"
            val intent = Intent(this, HousekeepingMenu::class.java)
            intent.putExtra("AdminStaff", x)
            startActivity(intent)
        }

        toLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
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
                var x = "Christal"
                val intent = Intent(this, FrontDesk::class.java)
                intent.putExtra("FrontDeskStaff",x)
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
}