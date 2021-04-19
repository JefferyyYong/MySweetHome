package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
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

        //for database use
        lostFoundList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("lostFoundTable")
        listView = findViewById(R.id.listview)

        val addBtn= findViewById<Button>(R.id.btnAdd)
        val editBtn= findViewById<Button>(R.id.btnEdit)

        addBtn.setOnClickListener {
            val intent = Intent(this, LostFoundAdd::class.java)
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
                    //this.setAdapter(adapter)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        });

        listView.setOnItemClickListener{
                parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            //Toast.makeText(applicationContext, "found", Toast.LENGTH_LONG).show()

            //Store value into variable
            //val row = listView.getItemIdAtPosition(position).toString()
            //val row = listView.getItemIdAtPosition(position).toString()
            //val id = listView.getItemAtPosition(row)
            val num = (view?.findViewById<View>(R.id.tvNo) as TextView).text.toString()
            val date = (view?.findViewById<View>(R.id.tvDate) as TextView).text.toString()
            val location = (view?.findViewById<View>(R.id.tvLocation) as TextView).text.toString()
            val item = (view?.findViewById<View>(R.id.tvItem) as TextView).text.toString()
            val status = (view?.findViewById<View>(R.id.tvStatus) as TextView).text.toString()

            Toast.makeText(getApplicationContext(), num, Toast.LENGTH_SHORT).show();

            editBtn.setOnClickListener {
                val intent = Intent(this, LostFoundEdit::class.java)
                intent.putExtra("selected_id", num)
                //intent.putExtra("selected_row", row) //Start from row 0
                intent.putExtra("selected_date", date)
                intent.putExtra("selected_loc", location)
                intent.putExtra("selected_item", item)
                intent.putExtra("selected_status", status)
                startActivity(intent)
            }

            //Toast.makeText(getApplicationContext(), "Date: " + date +"\n" +"Location : " + location +"\n"
            //            +"Item: " +item +"\n" +"Status: " +status, Toast.LENGTH_SHORT).show();
        }

    }

    //outside onCreate()
    //back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}