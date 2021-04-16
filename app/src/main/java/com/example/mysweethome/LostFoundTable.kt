package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.database.*

class LostFoundTable : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var lostFoundList: MutableList<LostFound>
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lost_found_table)

        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Lost and Found"
        actionbar.setDisplayHomeAsUpEnabled(true)

        lostFoundList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("lostFoundTable")
        listView = findViewById(R.id.listview)

        val addBtn= findViewById<Button>(R.id.btnAdd)
        val editBtn= findViewById<Button>(R.id.btnEdit)

        addBtn.setOnClickListener {
            val intent = Intent(this, LostFoundAdd::class.java)
            startActivity(intent)
        }

        editBtn.setOnClickListener {
            val intent = Intent(this, LostFoundEdit::class.java)
            startActivity(intent)
        }

        //To read value from firebase
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //The DataSnapshot will contain all the lostfound store in the database

                //Need to check if there is data in the database
                if(snapshot!!.exists()){
                    for(h in snapshot.children){
                        val lf = h.getValue(LostFound::class.java)
                        lostFoundList.add(lf!!)
                    }

                    val adapter = LostFoundAdapter(applicationContext, R.layout.lostfound_listview_row, lostFoundList)
                    listView.adapter = adapter
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        });
    }

    //back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}