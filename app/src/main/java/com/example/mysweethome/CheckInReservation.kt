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

class CheckInReservation : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    lateinit var id: TextView
    lateinit var name: TextView
    lateinit var pax: TextView
    lateinit var rIn: TextView
    lateinit var rOut: TextView
    lateinit var rType: TextView
    lateinit var rId: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_in_reservation)

        rId = findViewById(R.id.idSearch)
        val sBtn = findViewById<Button>(R.id.searchBtn3)
        id = findViewById(R.id.id)
        name = findViewById(R.id.rName)
        pax =  findViewById(R.id.rPax)
        rIn =  findViewById(R.id.rCheckDate)
        rOut = findViewById(R.id.rOutDate)
        rType = findViewById(R.id.rType)
        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Check In"
        actionbar.setDisplayHomeAsUpEnabled(true)



        sBtn.setOnClickListener {
            search()
            var rId : String = id.text.toString()
            var rName : String = name.text.toString()
            var rType : String = rType.text.toString()
            var rPax : String = pax.text.toString()
            var rIn : String = rIn.text.toString()
            var rOut : String = rOut.text.toString()
            val intent = Intent(this, CheckInReservation2::class.java)
            intent.putExtra("ReservationId",rId)
            intent.putExtra("ReservationName",rName)
            intent.putExtra("ReservationType",rType)
            intent.putExtra("ReservationPax",rPax)
            intent.putExtra("ReservationIn",rIn)
            intent.putExtra("ReservationOut",rOut)
            startActivity(intent)
        }


    }

    private fun search() {
        ref = FirebaseDatabase.getInstance().getReference().child("custReservationTable").child(rId.text.toString())
        ref.addValueEventListener(object : ValueEventListener {
            //proRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataS: DataSnapshot) {
                var data1 = dataS.child("id").getValue().toString()
                var data2 = dataS.child("name").getValue().toString()
                var data3 = dataS.child("roomType").getValue().toString()
                var data4 = dataS.child("pax").getValue().toString()
                var data5 = dataS.child("checkInDate").getValue().toString()
                var data6 = dataS.child("checkOutDate").getValue().toString()
                //val name3 = ds.child("roomType").getValue(DBReservation::class.java)
                //type.setText(name3.toString()
                id.setText(data1)
                name.setText(data2)
                rType.setText(data3)
                pax.setText(data4)
                rIn.setText(data5)
                rOut.setText(data6)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                //Don't ignore errors
            }
        })
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
            R.id.Item4 ->{
                val intent = Intent(this, RoomStatus::class.java)
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