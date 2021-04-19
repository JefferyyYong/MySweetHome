package com.example.mysweethome

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.database.FirebaseListAdapter
import com.firebase.ui.database.FirebaseListOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query


class ReservationHistory : AppCompatActivity() {

    lateinit var listView: ListView
    var list = ArrayList<String>()
   // lateinit var adapter: ArrayAdapter<String>
    lateinit var history: History
    lateinit var custEmail:String
    lateinit var fCustEmail:String
    var adapter: FirebaseListAdapter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reservation_history_listview)

        //ToolBars
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        var title = findViewById<TextView>(R.id.tvTittle)
        title.text = "Reservation History"

        val homeBtn = findViewById<ImageView>(R.id.ivHomepage)
        //val searchIcon = findViewById<ImageView>(R.id.ivSearch)
        val searchHis = findViewById<Button>(R.id.searchHis)
        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Reservation History"
        actionbar.setDisplayHomeAsUpEnabled(true)

        //Verify User
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val userEmail = user.email
            custEmail = userEmail.replace("@","")
            fCustEmail = custEmail.replace(".","")
            val ref = FirebaseDatabase.getInstance().getReference("custReservationTable").child(fCustEmail).orderByChild("email").equalTo(userEmail)
            getData(ref)
        } else {
            // No user is signed in
        }

        homeBtn.setOnClickListener {

            val intent = Intent(this, CustMenu::class.java)
            startActivity(intent)
        }

        /*val viewDetails = findViewById<TextView>(R.id.txtDetails)

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

        }*/
        searchHis.setOnClickListener {
            var x = fCustEmail
            val intent = Intent(this, Reservation_History_Details::class.java)
            intent.putExtra("FCustEmail",x)
            startActivity(intent)
        }

    }

    //back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

   /* private fun getData(query: Query) {
        listView = findViewById<ListView>(R.id.ListView)
        list = ArrayList()
        adapter = ArrayAdapter<String>(this, R.layout.demo_reservation_history, R.id.reservationInfo, list)
        history = History()
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (reservationSnapshot in dataSnapshot.children) {
                    history = reservationSnapshot.getValue(History::class.java)!!
                    list.add("Check-in Date: " + history.getCheckInDate() + "\n" + history.getCheckInDate())

                }

                listView.setAdapter(adapter);
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle possible errors.
            }
        })
    }*/

    //ListView
    private fun getData(query: Query) {
        listView = findViewById<ListView>(R.id.ListView)
        val options: FirebaseListOptions<History> = FirebaseListOptions.Builder<History>().setLayout(R.layout.reservation_history)
                .setQuery(query, History::class.java)
                .build()

        adapter = object : FirebaseListAdapter<History>(options) {
            override fun populateView(v: View, model: History, position: Int) {
                val checkInDate = v.findViewById<TextView>(R.id.lbCheckInDate)
                val checkOutDate = v.findViewById<TextView>(R.id.lbCheckOutDate)
                val checkInTime = v.findViewById<TextView>(R.id.lbCheckInTime)
                val roomType = v.findViewById<TextView>(R.id.lbRoom)
                val pax = v.findViewById<TextView>(R.id.lbPax)
                val totalAmount = v.findViewById<TextView>(R.id.lbTotal)

                checkInDate.text = "Check-in Date: " + model.checkInDate.toString()
                checkOutDate.text ="Check-out Date: " +  model.checkOutDate.toString()
                checkInTime.text = "Check-in Time: " + model.checkInTime.toString()
                roomType.text = model.roomType.toString()
                pax.text = model.pax.toString() + " pax"
                totalAmount.text = "Amount Paid: " + model.totalAmount.toString()
            }
        }
        listView.setAdapter(adapter);
    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }



}