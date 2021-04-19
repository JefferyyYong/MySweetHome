package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.database.*

class InspectFloor : AppCompatActivity() {

    //DB use
    lateinit var spinnerFloor: Spinner
    lateinit var spinnerStatus: Spinner
    lateinit var spinnerItem: Spinner
    lateinit var etRemarks: EditText
    lateinit var matchedId: Query

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inspect_floor)

        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Inspect by Floor"
        actionbar.setDisplayHomeAsUpEnabled(true)

        //DB use
        spinnerFloor = findViewById<Spinner>(R.id.spinnerFloor)
        spinnerItem = findViewById<Spinner>(R.id.spinnerItem)
        spinnerStatus = findViewById<Spinner>(R.id.spinnerStatus)
        etRemarks = findViewById<EditText>(R.id.etRemarks)
        //tvTemp = findViewById<TextView>(R.id.tvTemp)
        //

        //Confirm floor level (total)
        val floorLvl = arrayOf("Floor 1","Floor 2","Floor 3", "Floor 4", "Floor 5")
        val sFloor = findViewById<Spinner>(R.id.spinnerFloor)
        if (sFloor != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, floorLvl)
            sFloor.adapter = arrayAdapter

            /*
            sFloor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@InspectFloor, floorLvl[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }*/
        }

        //Item
        val item = arrayOf("Lighting", "Fire extinguisher", "Lift", "Corridor")
        val sItem = findViewById<Spinner>(R.id.spinnerItem)
        if (sItem != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, item)
            sItem.adapter = arrayAdapter

            /*
            sItem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@InspectFloor, item[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }*/
        }

        //Status
        val status = arrayOf("Checked", "Unchecked", "Further inspection needed")
        val sStatus = findViewById<Spinner>(R.id.spinnerStatus)
        if (sStatus != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, status)
            sStatus.adapter = arrayAdapter

            /*
            sStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@InspectFloor, status[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }*/
        }

        var btnList = findViewById<Button>(R.id.btnList)

        btnList.setOnClickListener {
            val intent = Intent(this, InspectFloorList::class.java)
            startActivity(intent)
        }

        val toSubmit = findViewById<Button>(R.id.btnSubmit)

        toSubmit.setOnClickListener {
            //To check if the id (floor + item) has filed inspection (whether it is matched)
            val ref = FirebaseDatabase.getInstance().reference
            //val matchId = ref.child("lostFoundTable").child(roomChecked.toString().trim())
            val selectedFloor = spinnerFloor.getSelectedItem().toString().trim()
            val selectedItem = spinnerItem.getSelectedItem().toString().trim()

            matchedId =  ref.child("lostFoundTable").child("id"+"item").equalTo(selectedFloor + selectedItem)

            matchedId.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle possible errors.
                }
            })

            saveFloorInspect()

        }
    }

    //Outside oncreate
    private fun saveFloorInspect(){
        //need to save date, location, item, status
        val floor = spinnerFloor.getSelectedItem().toString()
        val item = spinnerItem.getSelectedItem().toString()
        val status = spinnerStatus.getSelectedItem().toString()
        val remarks = etRemarks.text.toString().trim()
        val ref = FirebaseDatabase.getInstance().getReference("floorInspectionTable")

        val id = floor.toString().trim() + item.toString().trim()

        val newFloorIns = InspectionFloor(id.toString(), floor, item, status, remarks)

        ref.child(id.toString()).setValue(newFloorIns).addOnCompleteListener{
            Toast.makeText(
                this@InspectFloor,
                "The floor has been inspected successfully",
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