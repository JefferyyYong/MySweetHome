package com.example.mysweethome

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class InspectRoom : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inspect_room)

        setTitle("Inspect by Room");

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
            val intent = Intent(this, InspectMenu::class.java)
            startActivity(intent)
        }
    }
}