package com.example.mysweethome

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class ReservationHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reservation_history)

        val viewDetails = findViewById<TextView>(R.id.txtDetails)

        viewDetails.setOnClickListener {
            val intent = Intent(this, ReservationHistoryDetails::class.java)
            startActivity(intent)
        }

        //Calendar
        val btnCalendar = findViewById<ImageView>(R.id.imgCalendar)
        val searchTxt  = findViewById<EditText>(R.id.searchCIDate)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        btnCalendar.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Date
                searchTxt.setText("" + dayOfMonth + "/" + month + "/" + year)
            }, year, month, day)
            dpd.show()

        }

    }
}