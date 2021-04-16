package com.example.mysweethome

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class CustReservation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cust_reservation)

        //Intent
        val proceedBtn = findViewById<Button>(R.id.proceedBtn)
        val cancelBtn = findViewById<Button>(R.id.cancelBtn)

        proceedBtn.setOnClickListener {
            val intent = Intent(this, CustPayment::class.java)
            startActivity(intent)
        }

        cancelBtn.setOnClickListener {
            val intent = Intent(this, CustMenu::class.java)
            startActivity(intent)
        }

        //Calendar
        val checkIn = findViewById<ImageView>(R.id.imgDate)
        val checkInTxt     = findViewById<EditText>(R.id.etDate)
        val checkOut = findViewById<ImageView>(R.id.imgDate2)
        val checkOutTxt     = findViewById<EditText>(R.id.etDate2)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        checkIn.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Date
                checkInTxt.setText("" + dayOfMonth + "/" + month + "/" + year)
            }, year, month, day)
            dpd.show()

        }
        checkOut.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                checkOutTxt.setText("" + dayOfMonth + "/" + month + "/" + year)
            }, year, month, day)
            dpd.show()

        }

    }
}