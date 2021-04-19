package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.lang.StringBuilder

class CheckOutMenu : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    lateinit var db:FirebaseDatabase
    lateinit var type:TextView
    lateinit var pax:TextView
    lateinit var checkIn:TextView
    lateinit var name:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_out_menu)

        val yes = findViewById<Button>(R.id.selectBtn1)
        val no = findViewById<Button>(R.id.selectBtn2)
        val search = findViewById<ImageButton>(R.id.searchBtn)
        val ic_field = findViewById<EditText>(R.id.icField)
        name = findViewById(R.id.custName)
        type = findViewById(R.id.roomType)
        pax = findViewById(R.id.pax)
        checkIn = findViewById(R.id.checkIn)

        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Check Out"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val givenString = ic_field.text

        search.setOnClickListener{
            ref = FirebaseDatabase.getInstance().getReference().child("reservation_table").child(givenString.toString())
            ref.addValueEventListener(object :ValueEventListener{
            //proRef.addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(dataS: DataSnapshot) {
                            var data1 = dataS.child("customerName").getValue().toString()
                            var data2 = dataS.child("roomType").getValue().toString()
                            var data3 = dataS.child("pax").getValue().toString()
                            var data4 = dataS.child("checkInDate").getValue().toString()
                            //val name3 = ds.child("roomType").getValue(DBReservation::class.java)
                            //type.setText(name3.toString()
                            name.setText(data1)
                            type.setText(data2)
                            pax.setText(data3)
                            checkIn.setText(data4)
                    }
                override fun onCancelled(databaseError: DatabaseError) {
                    //Don't ignore errors
                }
        })
        }

        yes.setOnClickListener {
            var customerName: String = name.text.toString()
            var customerIc: String = givenString.toString()
            val intent = Intent(this, CheckOutMenu2::class.java)
            intent.putExtra("CustomerName",customerName)
            intent.putExtra("CustomerIc",customerIc)
            startActivity(intent)
        }

        no.setOnClickListener {
            val intent = Intent(this, FrontDesk::class.java)
            startActivity(intent)
        }



    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Item1 -> {
                var x = "Jeffery"
                val intent = Intent(this, FrontDesk::class.java)
                intent.putExtra("FrontDeskStaff",x)
                startActivity(intent)
                return true
            }
            R.id.Item2 ->{
                val intent = Intent(this, CheckInMenu::class.java)
                startActivity(intent)
                return true
            }
            R.id.Item3 ->{
                val intent = Intent(this, CheckOutMenu::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}