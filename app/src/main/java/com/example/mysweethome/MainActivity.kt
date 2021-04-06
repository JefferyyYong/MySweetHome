package com.example.mysweethome


import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.app.DatePickerDialog
import android.widget.TextView
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setContentView(R.layout.customer_booking)
        //setContentView(R.layout.lost_found_table)


        val mPickTimeBtn = findViewById<Button>(R.id.pickDateBtn)
        val textView     = findViewById<TextView>(R.id.dateTv)
        val mPickTimeBtn2 = findViewById<Button>(R.id.pickDateBtn2)
        val textView2     = findViewById<TextView>(R.id.dateTv2)

        //check_in_3
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        //customer_booking


        mPickTimeBtn.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                textView.setText("" + dayOfMonth + "/" + month + "/" + year)
            }, year, month, day)
            dpd.show()

        }
        mPickTimeBtn2.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                textView2.setText("" + dayOfMonth + "/" + month + "/" + year)
            }, year, month, day)
            dpd.show()

        }//check_in_3

        val personNames = arrayOf("Single bed room","Double bed room","Deluxe room","Queen room")
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, personNames)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " + personNames[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }


        }


        //For task allocation
        val staffNames = arrayOf("John", "Lily")
        val floorLevels = arrayOf("Floor1", "Floor2")
        val spinnerStaff = findViewById<Spinner>(R.id.spinnerStaff)
        val spinnerFloor = findViewById<Spinner>(R.id.spinnerFloor)
        if (spinnerStaff != null && spinnerFloor != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, personNames)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    Toast.makeText(this@MainActivity, getString(R.string.ddl_staff) + " " + staffNames[position], Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@MainActivity, getString(R.string.ddl_floor) + " " + floorLevels[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }



    }
}
