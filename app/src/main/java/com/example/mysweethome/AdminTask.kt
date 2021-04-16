package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class AdminTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_task)

        setTitle("Task Allocation");

        //The staff name need to get from database
        val staffName = arrayOf("Staff","John","Alice","Apple")
        val sStaff = findViewById<Spinner>(R.id.spinnerStaff)
        if (sStaff != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, staffName)
            sStaff.adapter = arrayAdapter

            sStaff.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@AdminTask, staffName[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }

        //Confirm floor level (total)
        val floorLvl = arrayOf("Floor", "Floor 1","Floor 2","Floor 3")
        val sFloor = findViewById<Spinner>(R.id.spinnerFloor)
        if (sFloor != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, floorLvl)
            sFloor.adapter = arrayAdapter

            sFloor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@AdminTask, floorLvl[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }

        val toSubmit = findViewById<Button>(R.id.btnSubmit)

        toSubmit.setOnClickListener {
            val intent = Intent(this, HousekeepingMenu::class.java)
            startActivity(intent)
        }
    }
}