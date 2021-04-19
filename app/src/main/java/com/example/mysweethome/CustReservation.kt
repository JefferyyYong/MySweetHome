package com.example.mysweethome

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class CustReservation : AppCompatActivity() {

    //Database
    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var etPhone: EditText
    lateinit var spinner2: Spinner
    lateinit var etNumber: EditText
    lateinit var etDate: EditText
    lateinit var etDate2: EditText
    lateinit var etTime: EditText
    lateinit var etTime2: EditText
    lateinit var txtAmount: TextView
    lateinit var txtReservationNo: TextView
    lateinit var custReservationId: String
    lateinit var date1:EditText
    lateinit var date2:EditText
    lateinit var totalPrice:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cust_reservation)

        //Toolbar
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        var title = findViewById<TextView>(R.id.tvTittle)
        title.text = "Reservation"

        val homeBtn = findViewById<ImageView>(R.id.ivHomepage)

        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Reservation"
        actionbar.setDisplayHomeAsUpEnabled(true)


        //Intent
        val proceedBtn = findViewById<Button>(R.id.proceedBtn)
        val cancelBtn = findViewById<Button>(R.id.cancelBtn)

        //Calendar
        val checkIn = findViewById<ImageView>(R.id.imgDate)
        val checkInTxt     = findViewById<EditText>(R.id.etDate)
        val checkOut = findViewById<ImageView>(R.id.imgDate2)
        val checkOutTxt     = findViewById<EditText>(R.id.etDate2)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        //Database
        etName =findViewById<EditText>(R.id.etName)
        etEmail=findViewById<EditText>(R.id.etEmail)
        etPhone=findViewById<EditText>(R.id.etPhone)
        spinner2=findViewById<Spinner>(R.id.spinner2)
        etNumber=findViewById<EditText>(R.id.etNumber)
        etDate=findViewById<EditText>(R.id.etDate)
        etDate2=findViewById<EditText>(R.id.etDate2)
        etTime=findViewById<EditText>(R.id.etTime)
        etTime2=findViewById<EditText>(R.id.etTime2)
        txtAmount=findViewById<EditText>(R.id.txtAmount)
        txtReservationNo=findViewById<EditText>(R.id.txtReservationNo)

        //Calculate Price
        date1 = findViewById<EditText>(R.id.etDate)
        date2 = findViewById<EditText>(R.id.etDate2)
        totalPrice = findViewById<Button>(R.id.btnCalculate)


        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val userEmail = user.email
            etEmail.setText(userEmail.toString())
        } else {
            // No user is signed in
        }

        totalPrice.setOnClickListener{

            val spinnerStatus = findViewById<Spinner>(R.id.spinner2)
            val checkInDate: Date
            val checkOutDate: Date
            val dates = SimpleDateFormat("MM/dd/yyyy")
            val checkInDate1 = date1.text.toString().trim()
            val checkOutDate2 = date2.text.toString().trim()
            checkInDate = dates.parse(checkInDate1)
            checkOutDate = dates.parse(checkOutDate2)
            val difference: Long = abs(checkOutDate.time - checkInDate.time)
            val differenceDates = difference / (24 * 60 * 60 * 1000)
            val dayDifference = differenceDates

            if (spinnerStatus.selectedItem.toString() == "Double bed room") {
                val price = 100
                val depositPrice = 50
                val totalPrice = price * dayDifference
                val finalTotal = totalPrice.plus(depositPrice)
                txtAmount.text ="RM" + finalTotal.toString().trim()
            }else {
                if (spinnerStatus.selectedItem.toString() == "Single bed room") {
                    val price = 70
                    val depositPrice = 50
                    val totalPrice1 = price * dayDifference
                    val finalTotal1 = totalPrice1.plus(depositPrice)
                    txtAmount.text ="RM" + finalTotal1.toString().trim()
                } else {
                    if (spinnerStatus.selectedItem.toString() == "Deluxe room") {
                        val price = 150
                        val depositPrice = 50
                        val totalPrice2 = price * dayDifference
                        val finalTotal2 = totalPrice2.plus(depositPrice)
                        txtAmount.text ="RM" + finalTotal2.toString().trim()
                    } else {
                        if (spinnerStatus.selectedItem.toString() == "Queen room") {
                            val price = 200
                            val depositPrice = 50
                            val totalPrice3 = price * dayDifference
                            val finalTotal3 = totalPrice3.plus(depositPrice)
                            txtAmount.text ="RM" + finalTotal3.toString().trim()
                        }
                    }

                }
            }
        }

        checkIn.setOnClickListener {

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Date
                    checkInTxt.setText("" + (monthOfYear+1) + "/" + dayOfMonth + "/" + year)
                },
                year,
                month,
                day
            )
            dpd.show()
        }

        checkOut.setOnClickListener {

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    checkOutTxt.setText("" + (monthOfYear+1) + "/" + dayOfMonth + "/" + year)
                },
                year,
                month,
                day
            )
            dpd.show()
        }

        val itemStatus = arrayOf("Double bed room", "Single bed room", "Deluxe room", "Queen room")
        val spinnerStatus = findViewById<Spinner>(R.id.spinner2)

        if (spinnerStatus != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemStatus)
            spinnerStatus.adapter = arrayAdapter


            spinnerStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    //Toast.makeText(this@CustReservation, itemStatus[position], Toast.LENGTH_SHORT)
                      //  .show()

                    /*val text: String = parent?.getItemAtPosition(position).toString()

                    if(text.equals("Double bed room")){
                        txtAmount.text = "RM 300"
                    }

                    if(text.equals("Single bed room")){
                        txtAmount.text = "RM 200"
                    }

                    if(text.equals("Deluxe room")){
                        txtAmount.text = "RM 800"
                    }

                    if(text.equals("Queen room")){
                        txtAmount.text = "RM 600"
                    }*/

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }


        val ref = FirebaseDatabase.getInstance().getReference("custReservationTable")
        var reservationId = 0


        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                reservationId = snapshot.childrenCount.toInt() + 1
                txtReservationNo.setText("reservationNo"+reservationId.toString())
            }

            override fun onCancelled(error: DatabaseError) {
            }
        });

        homeBtn.setOnClickListener {
            val intent = Intent(this, CustMenu
            ::class.java)
            startActivity(intent)
        }

        proceedBtn.setOnClickListener {
            saveReservation()
        }

        cancelBtn.setOnClickListener {
            val intent = Intent(this, CustMenu::class.java)
            startActivity(intent)
        }

    }

    //back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



   private fun saveReservation(){
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phoneNo = etPhone.text.toString().trim()
        val roomType = spinner2.getSelectedItem().toString()
        val pax = etNumber.text.toString().trim()
        val checkInDate = etDate.text.toString().trim()
        val checkOutDate = etDate2.text.toString().trim()
        val checkInTime = etTime.text.toString().trim()
        val checkOutTime = etTime2.text.toString().trim()
        val totalAmount = txtAmount.text.toString().trim()

       //Validation
       if(name.isEmpty()){
           etName.error = "Please fill in your name"
           return
       }

       if (email.isEmpty()){
           etEmail.error = "Please fill in email"
           return
       }

       if(phoneNo.isEmpty()){
           etPhone.error = "Please fill in your contact"
           return
       }

       if(pax.isEmpty()){
           etNumber.error = "Please fill in pax"
           return
       }

       if(checkInDate.isEmpty()){
           etDate.error = "Please select check in date"
           return
       }

       if(checkOutDate.isEmpty()){
           etDate2.error = "Please select check out date"
           return
       }

       if(checkInTime.isEmpty()){
           etTime.error = "Please fill in check in time"
           return
       }
       if(checkOutTime.isEmpty()){
           etTime2.error = "Please fill in check out time"
           return
       }


       val ref = FirebaseDatabase.getInstance().getReference("custReservationTable")
       //Auto Generate
       //val custReservationId = ref.push().key

       custReservationId = txtReservationNo.text.toString().trim()

       val custReservation = DBCustReservation(
           custReservationId.toString(),
           name,
           email,
           phoneNo,
           roomType,
           pax,
           checkInDate,
           checkOutDate,
           checkInTime,
           checkOutTime,
           totalAmount
       )

       ref.child(custReservationId.toString()).setValue(custReservation).addOnCompleteListener{
            Toast.makeText(applicationContext, "Reservation details has been recorded successfully", Toast.LENGTH_LONG).show()
        }

       val intent = Intent(this, CustPayment::class.java)
       startActivity(intent)
    }
}

