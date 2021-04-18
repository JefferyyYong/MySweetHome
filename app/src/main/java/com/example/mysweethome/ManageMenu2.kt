package com.example.mysweethome

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.properties.Delegates

class ManageMenu2 : AppCompatActivity() {
    lateinit var type: TextView
    lateinit var ePrice: TextView
    lateinit var newCheckOut: TextView
    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manage_menu2)

        val name = findViewById<TextView>(R.id.cName1)
        val update = findViewById<Button>(R.id.updateBtn)
        newCheckOut = findViewById(R.id.checkOut2)
        val date = findViewById<Button>(R.id.date2)
        val generate = findViewById<Button>(R.id.generateP)
        type = findViewById(R.id.type2)
        ePrice = findViewById(R.id.ePrice)
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Manage"
        actionbar.setDisplayHomeAsUpEnabled(true)

        name.setText(intent.getStringExtra("CustomerName").toString())
        type.setText(intent.getStringExtra("roomType").toString())


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        date.setOnClickListener {
            val dpd = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        // Display Selected date in TextView
                        newCheckOut.setText("" + month + "/" + dayOfMonth + "/" + year)
                    },
                    year,
                    month,
                    day
            )
            dpd.show()
        }//check_in_3
        //-------------------------------------------------------------------------------


//-----------------------------------------------------------------------------------------
        generate.setOnClickListener {
            val sDate: Date
            val lDate: Date
            val dates = SimpleDateFormat("MM/dd/yyyy")
            val sDate1 = intent.getStringExtra("CustomerCheckIn").toString() //old check in
            val lDate2 = intent.getStringExtra("CustomerCheckOut").toString() //old check out
            sDate = dates.parse(sDate1)
            lDate = dates.parse(lDate2)
            val difference: Long = abs(lDate.time - sDate.time)
            val differenceDates = difference / (24 * 60 * 60 * 1000)
            val dayDifference = differenceDates
            //calculate price
            if (type.text.toString() == "Double bed room") {
                val price = 100
                val depositPrice = 50
                val totalPrice = price * dayDifference
                val ofinalTotal = totalPrice.plus(depositPrice)
                calculateNew(ofinalTotal)
            } else {
                if (type.text.toString() == "Single bed room") {
                    val price = 70
                    val depositPrice = 50
                    val totalPrice1 = price * dayDifference
                    val ofinalTotal1 = totalPrice1.plus(depositPrice)
                    calculateNew(ofinalTotal1)
                } else {
                    if (type.text.toString() == "Deluxe room") {
                        val price = 150
                        val depositPrice = 50
                        val totalPrice2 = price * dayDifference
                        val ofinalTotal2 = totalPrice2.plus(depositPrice)
                        calculateNew(ofinalTotal2)
                    } else {
                        if (type.text.toString() == "Queen room") {
                            val price = 200
                            val depositPrice = 50
                            val totalPrice3 = price * dayDifference
                            val ofinalTotal3 = totalPrice3.plus(depositPrice)
                            calculateNew(ofinalTotal3)
                        }
                    }

                }
            }
        }
        update.setOnClickListener {
            val checkOutDateNew = newCheckOut.text.toString().trim()
            updateData(checkOutDateNew)
            //ref = FirebaseDatabase.getInstance().getReference().child("reservation_table").child(intent.getStringExtra("customerIc").toString())
            //ref.child("checkOutDate").(newCheckOut.text.toString().trim())

            val intent = Intent(this, FrontDesk::class.java)
            startActivity(intent)
        }

    }

    private fun updateData(checkOutDateNew: String) {
        ref = FirebaseDatabase.getInstance().getReference("reservation_table")
    val data = mapOf<String,String>(
            "checkOutDate" to checkOutDateNew
    )
        ref.child(intent.getStringExtra("customerIc").toString()).updateChildren(data).addOnSuccessListener {
            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Failed to Updated",Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateNew(x: Long) {
        val sDate: Date
        val lDate: Date
        val dates = SimpleDateFormat("MM/dd/yyyy")
        val sDate1 = intent.getStringExtra("CustomerCheckIn").toString() //old check in
        val lDate2 = newCheckOut.text.toString().trim()
        sDate = dates.parse(sDate1)
        lDate = dates.parse(lDate2)
        val difference: Long = abs(lDate.time - sDate.time)
        val differenceDates = difference / (24 * 60 * 60 * 1000)
        val dayDifference = differenceDates
        //calculate price
        if (type.text.toString() == "Double bed room") {
            val price = 100
            val depositPrice = 50
            val totalPrice = price * dayDifference
            val ofinalTotal = totalPrice.plus(depositPrice)
            val finalTotal = ofinalTotal.minus(x)
            ePrice.setText("RM" + finalTotal.toString().trim())
        } else {
            if (type.text.toString() == "Single bed room") {
                val price = 70
                val depositPrice = 50
                val totalPrice1 = price * dayDifference
                val ofinalTotal1 = totalPrice1.plus(depositPrice)
                val finalTotal1 = ofinalTotal1.minus(x)
                ePrice.setText("RM" + finalTotal1.toString().trim())
            } else {
                if (type.text.toString() == "Deluxe room") {
                    val price = 150
                    val depositPrice = 50
                    val totalPrice2 = price * dayDifference
                    val ofinalTotal2 = totalPrice2.plus(depositPrice)
                    val finalTotal2 = ofinalTotal2.minus(x)
                    ePrice.setText("RM" + finalTotal2.toString().trim())
                } else {
                    if (type.text.toString() == "Queen room") {
                        val price = 200
                        val depositPrice = 50
                        val totalPrice3 = price * dayDifference
                        val ofinalTotal3 = totalPrice3.plus(depositPrice)
                        val finalTotal3 = ofinalTotal3.minus(x)
                        ePrice.setText("RM" + finalTotal3.toString().trim())

                    }
                }

            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}