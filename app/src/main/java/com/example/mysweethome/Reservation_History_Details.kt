package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.database.*

class Reservation_History_Details : AppCompatActivity() {
    lateinit var rId : EditText
    lateinit var name3:TextView
    lateinit var email3:TextView
    lateinit var checkIn3:TextView
    lateinit var checkOut3:TextView
    lateinit var pax3:TextView
    lateinit var phoneNo3:TextView
    lateinit var type3:TextView
    lateinit var amount3:TextView
    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation__history)

        name3 =findViewById(R.id.name2)
        email3 =findViewById(R.id.email2)
        phoneNo3 =findViewById(R.id.no)
        pax3 =findViewById(R.id.pax2)
        checkIn3 =findViewById(R.id.in2)
        checkOut3 =findViewById(R.id.out)
        type3 =findViewById(R.id.type4)
        amount3 =findViewById(R.id.amount2)

        val searchB = findViewById<ImageButton>(R.id.searchBtn4)
        rId = findViewById(R.id.searchT)



        searchB.setOnClickListener {
            search()
            var name1 : String = name3.text.toString()
            var email1 : String = email3.text.toString()
            var phoneNo1 : String = phoneNo3.text.toString()
            var pax1 : String = pax3.text.toString()
            var checkIn1 : String = checkIn3.text.toString()
            var checkOut1 : String = checkOut3.text.toString()
            var type1 : String = type3.text.toString()
            var amount1 : String = amount3.text.toString()

            val intent = Intent(this, Reservation_History_Details2::class.java)
            intent.putExtra("Name",name1)
            intent.putExtra("Email",email1)
            intent.putExtra("No",phoneNo1)
            intent.putExtra("Pax",pax1)
            intent.putExtra("In",checkIn1)
            intent.putExtra("Out",checkOut1)
            intent.putExtra("Type",type1)
            intent.putExtra("Amount",amount1)
            startActivity(intent)
        }

    }

    private fun search() {
        val ref = FirebaseDatabase.getInstance().getReference().child("custReservationTable").child(intent.getStringExtra("FCustEmail").toString()).child(rId.text.toString())
        ref.addValueEventListener(object : ValueEventListener {
            //proRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataS: DataSnapshot) {
                var data1 = dataS.child("name").getValue().toString()
                var data2 = dataS.child("email").getValue().toString()
                var data3 = dataS.child("checkInDate").getValue().toString()
                var data4 = dataS.child("checkOutDate").getValue().toString()
                var data5 = dataS.child("pax").getValue().toString()
                var data6 = dataS.child("phoneNo").getValue().toString()
                var data7 = dataS.child("roomType").getValue().toString()
                var data8 = dataS.child("totalAmount").getValue().toString()
                //val name3 = ds.child("roomType").getValue(DBReservation::class.java)
                //type.setText(name3.toString()
                name3.setText(data1)
                email3.setText(data2)
                checkIn3.setText(data3)
                checkOut3.setText(data4)
                pax3.setText(data5)
                phoneNo3.setText(data6)
                type3.setText(data7)
                amount3.setText(data8)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                //Don't ignore errors
            }
        })
    }
}