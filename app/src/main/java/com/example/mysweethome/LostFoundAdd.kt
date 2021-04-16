package com.example.mysweethome

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class LostFoundAdd : AppCompatActivity() {
    //DB use
    lateinit var etDate: EditText
    lateinit var etLocation: EditText
    lateinit var etItem: EditText
    lateinit var spinnerStatus: Spinner
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lost_found_add)

        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Lost and Found - Add"
        actionbar.setDisplayHomeAsUpEnabled(true)


        //DB use
        etLocation = findViewById<EditText>(R.id.etLocation)
        etItem = findViewById<EditText>(R.id.etItem)
        spinnerStatus = findViewById<Spinner>(R.id.spinnerStatus)
        //

        //Lost and found date
        etDate= findViewById<EditText>(R.id.etDate)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        etDate.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                etDate.setText("" + dayOfMonth + "/" + month + "/" + year)
            }, year, month, day)
            dpd.show()
        }


        //Lost and found status (For add)
        val itemStatus = arrayOf("Lost", "Found")
        val spinnerStatus = findViewById<Spinner>(R.id.spinnerStatus)
        if (spinnerStatus != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemStatus)
            spinnerStatus.adapter = arrayAdapter

            spinnerStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(this@LostFoundAdd, itemStatus[position], Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }


        //Back after "Proceed" button pressed
        val toProceed = findViewById<Button>(R.id.btnProceed)

        toProceed.setOnClickListener {
            saveLostFound()
        }

    }

    //DB use
    private fun saveLostFound(){
        //need to save date, location, item, status
        val date = etDate.text.toString().trim()
        val location = etLocation.text.toString().trim()
        val item = etItem.text.toString().trim()
        val sStatus = spinnerStatus.getSelectedItem().toString()

        if(date.isEmpty()){
            etDate.error = "Please select a date"
            return
        }

        if(location.isEmpty()){
            etLocation.error = "Please enter the location"
            return
        }

        if(item.isEmpty()){
            etItem.error = "Please enter the item"
            return
        }


        /* will give reference from root node (if don't want pass anything)
        val ref = FirebaseDatabase.getInstance().reference*/

        //val xx = entityclass(value to pass, ...)

        //val lf = LostFound(date)
        val ref = FirebaseDatabase.getInstance().getReference("lostFoundTable")

        //to generate a unique key
        val lfId = ref.push().key

        val lf = LostFound(lfId.toString(), date, location, item, sStatus)

        ref.child(lfId.toString()).setValue(lf).addOnCompleteListener{
            Toast.makeText(applicationContext, "New lost found item saved successfully", Toast.LENGTH_LONG).show()
            val intent = Intent(this, LostFoundTable::class.java)
            startActivity(intent)
        }
    }


    //Side menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_3, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Item1 -> {
                val intent = Intent(this, HkStaffMenu::class.java)
                startActivity(intent)
                return true
            }
            R.id.Item2 ->{
                val intent = Intent(this, LostFoundTable::class.java)
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