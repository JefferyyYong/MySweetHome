package com.example.mysweethome

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.util.*


class CustomerDetails : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_booking)

        // Write a message to the database
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
        val ic = database.getReference("customerIC")

        myRef.setValue("Hello, World!")

        val mPickTimeBtn = findViewById<Button>(R.id.pickDateBtn)
        val textView     = findViewById<TextView>(R.id.dateTv)
        val mPickTimeBtn2 = findViewById<Button>(R.id.pickDateBtn2)
        val textView2     = findViewById<TextView>(R.id.dateTv2)
        val nxtBtn        = findViewById<Button>(R.id.nextBtn)
        val sample        = findViewById<TextView>(R.id.sample)
        val sample2 =findViewById<TextView>(R.id.sample2)
        //check_in_3
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val customerName = intent.getStringExtra("CustomerName")
        val customerIc = intent.getStringExtra("CustomerIC")



        //customer_booking
        mPickTimeBtn.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    textView.setText("" + dayOfMonth + "/" + month + "/" + year)
                    ic.setValue("" + dayOfMonth + "/" + month + "/" + year)
                },
                year,
                month,
                day
            )
            dpd.show()

        }
        mPickTimeBtn2.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    textView2.setText("" + dayOfMonth + "/" + month + "/" + year)
                },
                year,
                month,
                day
            )
            dpd.show()

        }//check_in_3


        val personNames = arrayOf("Single bed room", "Double bed room", "Deluxe room", "Queen room")
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, personNames)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(
                        this@CustomerDetails,
                        getString(R.string.selected_item) + " " + personNames[position],
                        Toast.LENGTH_SHORT
                    ).show()
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }


        nxtBtn.setOnClickListener {
            //val db= Room.databaseBuilder(applicationContext,reservationDatabase::class.java,"Booking_details").build()
            //val db=reservationDatabase(this)
            val customer_name = customerName.toString()
            val customerIc = customerIc.toString()
            val checkInDate = textView.toString()
            val checkOutDate = textView2.toString()
            val roomType = Toast.LENGTH_SHORT.toString()

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue(String::class.java)
                    //val value2 = dataSnapshot.getValue()
                    Log.d("TAG", "Value is: $value")

                    sample.text = value
                    sample2.text = ic.toString()
                //sample2.text = customerIc.toString()
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException())
                }
            })

            ic.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue(String::class.java)
                    //val value2 = dataSnapshot.getValue()
                    Log.d("TAG", "Value is: $value")

                    sample2.text = value
                    //sample2.text = customerIc.toString()
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException())
                }
            })
            //db.reservationDAO().insert(reservationEntity(1,customer_name,customerIc,checkInDate,checkOutDate,roomType))
            //val intent = Intent(this, CheckInRoomStatus::class.java)
            //startActivity(intent)
        }
    }


}