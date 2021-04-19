package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.*

class HkStaffTask : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var loginName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hk_staff_task)

        val name = findViewById<TextView>(R.id.tvStaff)
        val floor = findViewById<TextView>(R.id.tvFloor)
        val remark = findViewById<TextView>(R.id.remark)

        val toBack = findViewById<Button>(R.id.btnBack)

        //
        loginName = intent.getStringExtra("HKS1").toString()

        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Staff Task"
        actionbar.setDisplayHomeAsUpEnabled(true)


            ref = FirebaseDatabase.getInstance().getReference().child("taskTable").child(loginName.toString())
            ref.addValueEventListener(object : ValueEventListener {
                //proRef.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(dataS: DataSnapshot) {
                    var data1 = dataS.child("staff").getValue().toString()
                    var data2 = dataS.child("floorNo").getValue().toString()
                    var data3 = dataS.child("description").getValue().toString()
                    //val name3 = ds.child("roomType").getValue(DBReservation::class.java)
                    //type.setText(name3.toString()
                    name.setText(data1)
                    floor.setText(data2)
                    remark.setText(data3)
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    //Don't ignore errors
                }
            })

        toBack.setOnClickListener {
            val intent = Intent(this, HkStaffMenu::class.java)
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
                intent.putExtra("HKS1",loginName)
                startActivity(intent)
                return true
            }
            R.id.Item2 -> {
                val intent = Intent(this, LostFoundTable::class.java)
                intent.putExtra("HKS1",loginName)
                startActivity(intent)
                return true
            }

            R.id.Item3 -> {
                val intent = Intent(this, HkStaffTask::class.java)
                intent.putExtra("HKS1",loginName)
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