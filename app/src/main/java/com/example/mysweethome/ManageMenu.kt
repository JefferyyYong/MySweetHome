package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.*

class ManageMenu : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    lateinit var db: FirebaseDatabase
    lateinit var type:TextView
    lateinit var checkOut:TextView
    lateinit var name:TextView
    lateinit var ic:TextView
    lateinit var checkIn:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manage_menu)

        val changeBtn = findViewById<Button>(R.id.changeBtn)
        val icField = findViewById<EditText>(R.id.cIc)
        ic = findViewById(R.id.customerIc2)
        name = findViewById(R.id.name)
        type = findViewById(R.id.type)
        checkOut = findViewById(R.id.checkOut)
        checkIn = findViewById(R.id.checkIn3)
        val search = findViewById<Button>(R.id.search)
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Manage"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val givenString = icField.text

        search.setOnClickListener {
            ref = FirebaseDatabase.getInstance().getReference().child("reservation_table").child(givenString.toString())
            ref.addValueEventListener(object : ValueEventListener {
                //proRef.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(dataS: DataSnapshot) {
                    var data1 = dataS.child("customerIc").getValue().toString()
                    var data2 = dataS.child("customerName").getValue().toString()
                    var data3 = dataS.child("roomType").getValue().toString()
                    var data4 = dataS.child("checkOutDate").getValue().toString()
                    var data5 = dataS.child("checkInDate").getValue().toString()
                    //val name3 = ds.child("roomType").getValue(DBReservation::class.java)
                    //type.setText(name3.toString()
                    ic.setText(data1)
                    name.setText(data2)
                    type.setText(data3)
                    checkOut.setText(data4)
                    checkIn.setText(data5)
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    //Don't ignore errors
                }
            })
        }
        changeBtn.setOnClickListener {
            var customerName: String = name.text.toString()
            var customerIc: String = ic.text.toString()
            var type: String = type.text.toString()
            var checkO: String = checkOut.text.toString()
            var checkI: String = checkIn.text.toString()
            val intent = Intent(this, ManageMenu2::class.java)
            intent.putExtra("CustomerName",customerName)
            intent.putExtra("CustomerIC",customerIc)
            intent.putExtra("roomType",type)
            intent.putExtra("CustomerCheckOut",checkO)
            intent.putExtra("CustomerCheckIn",checkI)
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