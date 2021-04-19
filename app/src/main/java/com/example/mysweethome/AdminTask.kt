package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

class AdminTask : AppCompatActivity() {
    lateinit var sStaff: Spinner
    lateinit var sFloor: Spinner
    lateinit var remark:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_task)

        remark = findViewById(R.id.remark)
        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Task Allocation"
        actionbar.setDisplayHomeAsUpEnabled(true)



        //The staff name need to get from database
        //val staffName = arrayOf("Staff","John","Alice","Apple")
        val staffName = arrayOf("John","Alice","Apple")
        sStaff = findViewById(R.id.spinnerStaff)
        if (sStaff != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, staffName)
            sStaff.adapter = arrayAdapter

            /*
            sStaff.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    //Toast.makeText(this@AdminTask, staffName[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }*/
        }

        //Confirm floor level (total)
        //val floorLvl = arrayOf("Floor", "Floor 1","Floor 2","Floor 3")
        val floorLvl = arrayOf("Floor 2","Floor 3","Floor 4","Floor 5")
        sFloor = findViewById(R.id.spinnerFloor)
        if (sFloor != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, floorLvl)
            sFloor.adapter = arrayAdapter

            /*
            sFloor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    //Toast.makeText(this@AdminTask, floorLvl[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }*/
        }

        val toSubmit = findViewById<Button>(R.id.btnSubmit)
        val nxt = findViewById<Button>(R.id.btnSubmit2)

        toSubmit.setOnClickListener {
            saveTask()
        }

        nxt.setOnClickListener {
            val intent = Intent(this, AdminTaskList::class.java)
            startActivity(intent)
        }
    }

    private fun saveTask() {
        val staff = sStaff.selectedItem.toString()
        val floor = sFloor.selectedItem.toString()
        val remark = remark.text.toString().trim()

        val ref = FirebaseDatabase.getInstance().getReference("task_table")
        val lfId = staff
        val lf = DBTask(staff,floor,remark)
        ref.child(lfId.toString()).setValue(lf).addOnCompleteListener{
            Toast.makeText(applicationContext, "Task saved successfully", Toast.LENGTH_LONG).show()
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