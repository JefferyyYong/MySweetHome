package com.example.mysweethome

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


class CustomerDetails : AppCompatActivity() {
    lateinit var textView:TextView
    lateinit var textView2:TextView
    lateinit var paxNo: EditText
    lateinit var sSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_booking)

        val mPickTimeBtn = findViewById<Button>(R.id.pickDateBtn)
        val mPickTimeBtn2 = findViewById<Button>(R.id.pickDateBtn2)
        val nxtBtn = findViewById<Button>(R.id.nextBtn)
        val priceG = findViewById<Button>(R.id.priceGener)
        val roomPrice = findViewById<TextView>(R.id.price)
        val deposit = findViewById<TextView>(R.id.deposit)
        val total = findViewById<TextView>(R.id.totalPrice)
        textView = findViewById<TextView>(R.id.dateTv)
        textView2 = findViewById<TextView>(R.id.dateTv2)
        paxNo = findViewById<EditText>(R.id.paxNo)
        sSpinner = findViewById<Spinner>(R.id.spinner)



        //check_in_3
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val personNames = arrayOf("Single bed room", "Double bed room", "Deluxe room", "Queen room")
        val sSpinner = findViewById<Spinner>(R.id.spinner)
        if (sSpinner != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, personNames)
            sSpinner.adapter = arrayAdapter


        }

        //customer_booking
        mPickTimeBtn.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    textView.setText("" + month + "/" + dayOfMonth + "/" + year)
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
                    textView2.setText("" + month + "/" + dayOfMonth + "/" + year)
                },
                year,
                month,
                day
            )
            dpd.show()

        }//check_in_3



        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Check In"
        actionbar.setDisplayHomeAsUpEnabled(true)


        nxtBtn.setOnClickListener {
            saveReservation()
            val intent = Intent(this, CheckInRoomStatus::class.java)
            startActivity(intent)
        }

        //compare date
        priceG.setOnClickListener {
            val sDate: Date
            val lDate: Date
            val dates = SimpleDateFormat("MM/dd/yyyy")
            val sDate1 = textView.text.toString().trim()
            val lDate2 = textView2.text.toString().trim()
            sDate = dates.parse(sDate1)
            lDate = dates.parse(lDate2)
            val difference: Long = abs(lDate.time - sDate.time)
            val differenceDates = difference / (24 * 60 * 60 * 1000)
            val dayDifference = differenceDates
            //calculate price
            if (sSpinner.selectedItem.toString() == "Double bed room") {
                val price = 100
                val depositPrice = 50
                val totalPrice = price * dayDifference
                val finalTotal = totalPrice.plus(depositPrice)
                roomPrice.setText("RM" + totalPrice.toString().trim())
                deposit.setText("RM50")
                total.setText("RM" + finalTotal.toString().trim())
            } else {
                if (sSpinner.selectedItem.toString() == "Single bed room") {
                    val price = 70
                    val depositPrice = 50
                    val totalPrice1 = price * dayDifference
                    val finalTotal1 = totalPrice1.plus(depositPrice)
                    roomPrice.setText("RM" + totalPrice1.toString().trim())
                    deposit.setText("RM50")
                    total.setText("RM" + finalTotal1.toString().trim())
                } else {
                    if (sSpinner.selectedItem.toString() == "Deluxe room") {
                        val price = 150
                        val depositPrice = 50
                        val totalPrice2 = price * dayDifference
                        val finalTotal2 = totalPrice2.plus(depositPrice)
                        roomPrice.setText("RM" + totalPrice2.toString().trim())
                        deposit.setText("RM50")
                        total.setText("RM" + finalTotal2.toString().trim())
                    } else {
                        if (sSpinner.selectedItem.toString() == "Queen room") {
                            val price = 200
                            val depositPrice = 50
                            val totalPrice3 = price * dayDifference
                            val finalTotal3 = totalPrice3.plus(depositPrice)
                            roomPrice.setText("RM" + totalPrice3.toString().trim())
                            deposit.setText("RM50")
                            total.setText("RM" + finalTotal3.toString().trim())
                        }
                    }

                }
            }
        }
    }


    private fun saveReservation() {
        val customerName = intent.getStringExtra("CustomerName").toString()
        val customerIc = intent.getStringExtra("CustomerIC").toString()
        val roomType = sSpinner.selectedItem.toString()
        val check_in_date = textView.text.toString().trim()
        val check_out_date = textView2.text.toString().trim()
        val paxNo = paxNo.text.toString().trim()

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