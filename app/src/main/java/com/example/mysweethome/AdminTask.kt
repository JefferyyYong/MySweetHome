package com.example.mysweethome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class AdminTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_task)

        //The staff name need to get from database
        val staffName = arrayOf("John","Alice","Apple")
        val sStaff = findViewById<Spinner>(R.id.spinnerStaff)
        if (sStaff != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, staffName)
            sStaff.adapter = arrayAdapter

            sStaff.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@AdminTask, getString(R.string.ddl_floor) + " " + staffName[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }

        //Confirm floor level (total)
        val floorLvl = arrayOf("Floor 1","Floor 2","Floor 3")
        val sFloor = findViewById<Spinner>(R.id.spinnerFloor)
        if (sFloor != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, staffName)
            sFloor.adapter = arrayAdapter

            sFloor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@AdminTask, getString(R.string.ddl_floor) + " " + floorLvl[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }
    }
}