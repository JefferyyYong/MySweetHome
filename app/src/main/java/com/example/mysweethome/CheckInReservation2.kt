package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class CheckInReservation2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_in_reservation2)

        val id = findViewById<TextView>(R.id.rrId)
        val name = findViewById<TextView>(R.id.rrName)
        val type = findViewById<TextView>(R.id.rrType)
        val pax = findViewById<TextView>(R.id.rrPax)
        val cid = findViewById<TextView>(R.id.rrCid)
        val cod = findViewById<TextView>(R.id.rrCod)
        val nxt = findViewById<TextView>(R.id.nxtBtn5)

        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Check In"
        actionbar.setDisplayHomeAsUpEnabled(true)

        id.setText(intent.getStringExtra("ReservationId").toString())
        name.setText(intent.getStringExtra("ReservationName").toString())
        type.setText(intent.getStringExtra("ReservationType").toString())
        pax.setText(intent.getStringExtra("ReservationPax").toString())
        cid.setText(intent.getStringExtra("ReservationIn").toString())
        cod.setText(intent.getStringExtra("ReservationOut").toString())

        nxt.setOnClickListener {
            saveReservation()
            val intent = Intent(this, CheckInRoomStatus::class.java)
            startActivity(intent)
        }

    }

    private fun saveReservation() {
        val customerName = intent.getStringExtra("ReservationName").toString()
        val customerIc = intent.getStringExtra("ReservationId").toString()
        val roomType = intent.getStringExtra("ReservationType").toString()
        val check_in_date = intent.getStringExtra("ReservationIn").toString()
        val check_out_date = intent.getStringExtra("ReservationOut").toString()
        val paxNo = intent.getStringExtra("ReservationPax").toString()

        val ref = FirebaseDatabase.getInstance().getReference("reservation_table")
        val lfId = customerIc
        val lf = DBReservation(customerIc,customerName, roomType, check_in_date,check_out_date,paxNo)
        ref.child(lfId.toString()).setValue(lf).addOnCompleteListener{
            Toast.makeText(applicationContext, "Reservation saved successfully", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}