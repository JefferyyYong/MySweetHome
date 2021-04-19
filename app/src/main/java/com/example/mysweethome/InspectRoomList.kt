package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.database.*

class InspectRoomList : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var irList: MutableList<InspectionRoom>
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inspect_room_list)

        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Inspection Room List"
        actionbar.setDisplayHomeAsUpEnabled(true)

        //for database use
        irList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("roomInspectionTable")
        listView = findViewById(R.id.listview)

        var btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            val intent = Intent(this, InspectMenu::class.java)
            startActivity(intent)
        }

        //To read value from firebase
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //The DataSnapshot will contain all the lostfound store in the database

                //Need to check if there is data in the database
                if(snapshot!!.exists()){
                    for(h in snapshot.children){
                        val ir = h.getValue(InspectionRoom::class.java)
                        irList.add(ir!!)
                    }

                    val adapter = InspectionRoomAdapter(applicationContext, R.layout.inspect_room_listview, irList)
                    listView.adapter = adapter
                    //this.setAdapter(adapter)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        });

    }

    //outside onCreate
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