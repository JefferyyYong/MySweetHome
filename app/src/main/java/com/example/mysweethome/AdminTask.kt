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


        val staffName = arrayOf("Alice","Apple","John")
        sStaff = findViewById(R.id.spinnerStaff)
        if (sStaff != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, staffName)
            sStaff.adapter = arrayAdapter

        }

        //Confirm floor level (total)
        val floorLvl = arrayOf("Floor 1", "Floor 2","Floor 3","Floor 4","Floor 5")
        sFloor = findViewById(R.id.spinnerFloor)
        if (sFloor != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, floorLvl)
            sFloor.adapter = arrayAdapter
        }

        val toSubmit = findViewById<Button>(R.id.btnSubmit)
        val toView = findViewById<Button>(R.id.btnView)

        toSubmit.setOnClickListener {
            saveTask()
        }

        toView.setOnClickListener {
            val intent = Intent(this, AdminTaskList::class.java)
            startActivity(intent)
        }
    }

    private fun saveTask() {
        val staff = sStaff.selectedItem.toString()
        val floor = sFloor.selectedItem.toString()
        val remark = remark.text.toString().trim()

        val ref = FirebaseDatabase.getInstance().getReference("taskTable")
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