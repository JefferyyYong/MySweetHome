package com.example.mysweethome

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import java.util.*
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*

class LostFoundEdit : AppCompatActivity() {

    //Firebase use
    //lateinit var listView: ListView
    //lateinit var lostFoundList: MutableList<LostFound>
    lateinit var ref: DatabaseReference
    //lateinit var listener:FirebaseAuth.AuthStateListener

    lateinit var id:String
    lateinit var lostFoundList: MutableList<LostFound>
    lateinit var lostFound: LostFound
    lateinit var loginName: String

    //DB use
    lateinit var etDate: EditText
    lateinit var etLocation: EditText
    lateinit var etItem: EditText
    lateinit var spinnerStatus: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lost_found_edit)

        //
        loginName = intent.getStringExtra("HKS1").toString()

        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Lost and Found - Edit"
        actionbar.setDisplayHomeAsUpEnabled(true)

        //Get selected row from previous page
        //listView.getChildAt(2)
        //intent.getSerializableExtra("lostFoundTable") as lostFoundTable

        val edit_id = intent.getStringExtra("selected_id").toString()
        val edit_date = intent.getStringExtra("selected_date").toString()
        val edit_loc = intent.getStringExtra("selected_loc").toString()
        val edit_item = intent.getStringExtra("selected_item").toString()
        val edit_status = intent.getStringExtra("selected_status").toString()

        //for database use
        lostFoundList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("lostFoundTable")
        //val selected : Query = ref.child("lostFoundTable")
        val selected : Query = ref.child("selected_row")

        //listView = findViewById(R.id.listview)


        //ID from the layout
        etDate = findViewById<EditText>(R.id.etDate)
        etLocation = findViewById<EditText>(R.id.etLocation)
        etItem = findViewById<EditText>(R.id.etItem)
        spinnerStatus = findViewById<Spinner>(R.id.spinnerStatus)

        //Put the selected row date into the edit view of layout
        etDate.setText(edit_date)
        etLocation.setText(edit_loc)
        etItem.setText(edit_item)
        //spinnerStatus = edit_status

        //Lost and found date
        val lfDate= findViewById<EditText>(R.id.etDate)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        lfDate.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                // Display Selected date in TextView
                lfDate.setText("" + day + "/" + (month+1) + "/" + year)
            }, year, month, day)
            dpd.show()
        }

        //Lost and found status (For edit)
        val itemStatus = arrayOf("Lost", "Found")
        val sStatus = findViewById<Spinner>(R.id.spinnerStatus)
        if (sStatus != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemStatus)
            sStatus.adapter = arrayAdapter

            //set the retrieved spinner data
            sStatus.setSelection(arrayAdapter.getPosition(edit_status))
        }



        val toUpdate = findViewById<Button>(R.id.btnUpdate)

        toUpdate.setOnClickListener {
            //Toast.makeText(applicationContext, edit_row, Toast.LENGTH_LONG).show()

            id = edit_id
            val date = etDate.text.toString()
            val location = etLocation.text.toString()
            val item = etItem.text.toString()
            val status = spinnerStatus.getSelectedItem().toString()

            updateLF(id, date, location, item, status)

        }

        val toDelete = findViewById<Button>(R.id.btnDelete)

        toDelete.setOnClickListener {
            //Toast.makeText(applicationContext, edit_row, Toast.LENGTH_LONG).show()

            id = edit_id
            val date = etDate.text.toString()
            val location = etLocation.text.toString()
            val item = etItem.text.toString()
            val status = spinnerStatus.getSelectedItem().toString()

            //delete(id, date, location, item, status)


            val ref = FirebaseDatabase.getInstance().getReference().child("lostFoundTable").child(intent.getStringExtra("selected_id").toString())
            ref.removeValue()
            Toast.makeText(applicationContext,"Deleted successfully!",Toast.LENGTH_LONG).show()

            val intent = Intent(this, LostFoundTable::class.java)
            startActivity(intent)

        }

    }

    private fun updateLF(id: String, date: String, location: String, item: String, status: String) {
        //private fun tryUpdate(query:Query){
        val theRef = ref.child(id)

        val lf = LostFound(id, date, location, item, status)
        theRef.setValue(lf)

        val intent = Intent(this, LostFoundTable::class.java)
        intent.putExtra("HKS1",loginName)
        startActivity(intent)
        /*
        query.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (lfSnapshot in snapshot.children){
                    lfSnapshot.child("item").ref.setValue("zzz")
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })*/
    }

    //outside oncreate()
    //Side menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_3, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Item1 -> {
                val intent = Intent(this, HkStaffMenu::class.java)
                intent.putExtra("HKS1",loginName)
                startActivity(intent)
                return true
            }
            R.id.Item2 -> {
                val intent = Intent(this, LostFoundTable::class.java)
                intent.putExtra("HKS1",loginName)
                startActivity(intent)
                return true
            }

            R.id.Item3 -> {
                val intent = Intent(this, HkStaffTask::class.java)
                intent.putExtra("HKS1",loginName)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

