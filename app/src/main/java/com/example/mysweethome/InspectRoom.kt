package com.example.mysweethome

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.util.*


class InspectRoom : AppCompatActivity() {

    //DB use
    lateinit var spinnerRoomNo: Spinner
    lateinit var spinnerStatus: Spinner
    lateinit var etRemarks: EditText
    lateinit var matchedId: Query


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inspect_room)

        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Inspect by Room"
        actionbar.setDisplayHomeAsUpEnabled(true)

        //DB use
        spinnerRoomNo = findViewById<Spinner>(R.id.spinnerRoomNo)
        spinnerStatus = findViewById<Spinner>(R.id.spinnerStatus)
        etRemarks = findViewById<EditText>(R.id.etRemarks)
        //tvTemp = findViewById<TextView>(R.id.tvTemp)

        //The room no. need to get from database?
        val roomNo = arrayOf("101","102","103","201","202", "203", "301","302","303","401","402", "403")
        val sRoom = findViewById<Spinner>(R.id.spinnerRoomNo)
        if (sRoom != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roomNo)
            sRoom.adapter = arrayAdapter

            sRoom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@InspectRoom, roomNo[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }

        //Status
        val status = arrayOf("Checked", "Unchecked", "Further inspection needed")
        val sStatus = findViewById<Spinner>(R.id.spinnerStatus)
        if (sStatus != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, status)
            sStatus.adapter = arrayAdapter

            sStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@InspectRoom, status[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }

        val toSubmit = findViewById<Button>(R.id.btnSubmit)

        toSubmit.setOnClickListener {
            //To check if the room no. has filed inspection (whether it is matched)
            val ref = FirebaseDatabase.getInstance().reference
            //val matchId = ref.child("lostFoundTable").child(roomChecked.toString().trim())
            val selectedId = spinnerRoomNo.getSelectedItem().toString().trim()

            matchedId =  ref.child("lostFoundTable").child("id").equalTo(selectedId)

            matchedId.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle possible errors.
                }
            })

            saveRoomInspect()
        }
    }

    //Outside oncreate
    private fun saveRoomInspect(){
        //need to save date, location, item, status
        val room = spinnerRoomNo.getSelectedItem().toString().trim()
        val status = spinnerStatus.getSelectedItem().toString().trim()
        val remarks = etRemarks.text.toString().trim()

        /* will give reference from root node (if don't want pass anything)
        val ref = FirebaseDatabase.getInstance().reference*/
        //val xx = entityclass(value to pass, ...)

        val ref = FirebaseDatabase.getInstance().getReference("roomInspectionTable")

        val id = room.toString().trim()

        val newRoomIns = InspectionRoom(id.toString(), room, status, remarks)

        ref.child(id.toString()).setValue(newRoomIns).addOnCompleteListener{
            Toast.makeText(
                this@InspectRoom,
                "The room has been inspection successfully",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(this, InspectMenu::class.java)
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