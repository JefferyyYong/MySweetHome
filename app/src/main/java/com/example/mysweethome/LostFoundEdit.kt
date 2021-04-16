package com.example.mysweethome

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*

class LostFoundEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lost_found_edit)

        setTitle("Lost and Found - Edit");

        //Lost and found date
        val lfDate= findViewById<EditText>(R.id.etDate)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        lfDate.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                lfDate.setText("" + dayOfMonth + "/" + month + "/" + year)
            }, year, month, day)
            dpd.show()
        }

        //Lost and found status (For edit)
        val itemStatus = arrayOf("Lost", "Found")
        val sStatus = findViewById<Spinner>(R.id.spinnerStatus)
        if (sStatus != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemStatus)
            sStatus.adapter = arrayAdapter

            sStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(this@LostFoundEdit, itemStatus[position], Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }

        val toUpdate = findViewById<Button>(R.id.btnUpdate)

        toUpdate.setOnClickListener {
            val intent = Intent(this, LostFoundTable::class.java)
            startActivity(intent)
        }
    }
}