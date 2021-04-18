package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.*

class AdminTaskList : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_task_list)

        val iName = findViewById<EditText>(R.id.sIName)
        val oName = findViewById<TextView>(R.id.sName)
        val floor = findViewById<TextView>(R.id.sFloor)
        val desc = findViewById<TextView>(R.id.sDescrip)
        val done = findViewById<Button>(R.id.done)
        val search = findViewById<Button>(R.id.rSearch)

        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Task Allocation"
        actionbar.setDisplayHomeAsUpEnabled(true)

        search.setOnClickListener {
            ref = FirebaseDatabase.getInstance().getReference().child("task_table").child(iName.text.toString())
            ref.addValueEventListener(object : ValueEventListener {
                //proRef.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(dataS: DataSnapshot) {
                    var data1 = dataS.child("staff").getValue().toString()
                    var data2 = dataS.child("floorNo").getValue().toString()
                    var data3 = dataS.child("description").getValue().toString()
                    //val name3 = ds.child("roomType").getValue(DBReservation::class.java)
                    //type.setText(name3.toString()
                    oName.setText(data1)
                    floor.setText(data2)
                    desc.setText(data3)
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    //Don't ignore errors
                }
            })
        }

        done.setOnClickListener {
            val intent = Intent(this, AdminTask::class.java)
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

    //Back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}