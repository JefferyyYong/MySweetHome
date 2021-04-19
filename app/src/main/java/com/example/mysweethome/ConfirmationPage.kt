package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.database.*

class ConfirmationPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirmation_page)

        val btnYes = findViewById<Button>(R.id.btnYes)
        val btnNo = findViewById<Button>(R.id.btnNo)

        //ToolBars
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        var title = findViewById<TextView>(R.id.tvTittle)
        title.text = "Payment"

        btnYes.setOnClickListener {
            Toast.makeText(applicationContext, "Successful Reserve", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ReservationHistory::class.java)
            startActivity(intent)

        }

        btnNo.setOnClickListener {
            cancelReservation()
            cancelPayment()
            val intent = Intent(this, CustMenu::class.java)
            startActivity(intent)
        }
    }


    private fun cancelReservation(){
        val ref = FirebaseDatabase.getInstance().getReference()
        val lastQuery: Query = ref.child("custReservationTable").orderByKey().limitToLast(1)


        lastQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot)
            {   for (reserveSnapshot in dataSnapshot.children) {
                val id = reserveSnapshot.child("id").value.toString()
                //Toast.makeText(this@CustPayment, id, Toast.LENGTH_LONG).show()
                val deleteQuery = ref.child("custReservationTable").orderByChild("id").equalTo(id)
                removeData(deleteQuery)
                break
            }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle possible errors.
            }
        })

    }

    private fun removeData(query: Query){
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (reservationSnapshot in dataSnapshot.children) {
                    reservationSnapshot.ref.removeValue()

                    break
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    private fun cancelPayment(){
        val ref = FirebaseDatabase.getInstance().getReference()
        val lastQuery: Query = ref.child("custPaymentTable").orderByKey().limitToLast(1)


        lastQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot)
            {   for (reserveSnapshot in dataSnapshot.children) {
                val id = reserveSnapshot.child("id").value.toString()
                //Toast.makeText(this@CustPayment, id, Toast.LENGTH_LONG).show()
                val deleteQuery = ref.child("custPaymentTable").orderByChild("id").equalTo(id)
                removePayment(deleteQuery)
                break
            }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle possible errors.
            }
        })

        Toast.makeText(applicationContext, "Cancel Reserve", Toast.LENGTH_LONG).show()

    }

    private fun removePayment(query: Query){
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (reservationSnapshot in dataSnapshot.children) {
                    reservationSnapshot.ref.removeValue()

                    break
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }
}