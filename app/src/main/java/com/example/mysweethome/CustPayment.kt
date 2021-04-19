package com.example.mysweethome

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


class CustPayment : AppCompatActivity() {

    //Database
    lateinit var card: RadioButton
    lateinit var etCardNo: EditText
    lateinit var etCardExpiry: EditText
    lateinit var etCVC: EditText
    lateinit var txtReceiptNo: TextView
    lateinit var paymentId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cust_payment)

        //ToolBars
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        var title = findViewById<TextView>(R.id.tvTittle)
        title.text = "Payment"

        val homeBtn = findViewById<ImageView>(R.id.ivHomepage)

        //back button
        val actionbar = supportActionBar
        //back button
        actionbar!!.title = "Payment"
        actionbar.setDisplayHomeAsUpEnabled(true)

        //Intent
        val btnPay= findViewById<Button>(R.id.btnPay)
        val cancelBtn = findViewById<Button>(R.id.btnCancelPay)

        //Database
        //creditCard =findViewById<RadioGroup>(R.id.creditCard)
        etCardNo=findViewById<EditText>(R.id.etCardNo)
        etCardExpiry=findViewById<EditText>(R.id.etCardExpiry)
        etCVC=findViewById<EditText>(R.id.etCVC)
        txtReceiptNo=findViewById<EditText>(R.id.txtReceiptNo)

        //RadioGroup
        val creditCard = findViewById<RadioGroup>(R.id.creditCard)

        val ref = FirebaseDatabase.getInstance().getReference("custPaymentTable")
        var payId = 0


        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                payId = snapshot.childrenCount.toInt() + 1
                txtReceiptNo.setText("receiptNo"+payId.toString())
            }

            override fun onCancelled(error: DatabaseError) {
            }
        });

        btnPay.setOnClickListener {
            savePayment()
        }

        homeBtn.setOnClickListener {
            cancelReservation()
        }

        cancelBtn.setOnClickListener {
            cancelReservation()
        }
    }

    //back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun savePayment(){
        //Radio Button
        val creditCard = findViewById<RadioGroup>(R.id.creditCard)
        val selectedId = creditCard.checkedRadioButtonId
        card = findViewById<RadioButton>(selectedId)

        val cardNo = etCardNo.text.toString().trim()
        val cardExpiry = etCardExpiry.text.toString().trim()
        val cvc = etCVC.text.toString().trim()
        val cardType = card.getText().toString()

        if(cardNo.isEmpty()){
            etCardNo.error = "Please fill in your card number"
            return
        }

        if (cardExpiry.isEmpty()){
            etCardExpiry.error = "Please fill in the card expiry"
            return
        }

        if(cvc.isEmpty()){
            etCVC.error = "Please fill in the cvc"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("custPaymentTable")

        //val custPaymentd = ref.push().key
        paymentId = txtReceiptNo.text.toString().trim()

        val custPayment = DBCustPayment(
            paymentId.toString(),
                cardType,
                cardNo,
                cardExpiry,
                cvc
        )

        ref.child(paymentId.toString()).setValue(custPayment).addOnCompleteListener{
            //Toast.makeText(applicationContext, "Your transaction are successfully", Toast.LENGTH_LONG).show()
        }

        val intent = Intent(this, ConfirmationPage::class.java)
        startActivity(intent)

    }


    private fun cancelReservation(){
        val ref = FirebaseDatabase.getInstance().getReference()
        val lastQuery: Query = ref.child("custReservationTable").orderByKey().limitToLast(1)


        lastQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot)
            {   for (reserveSnapshot in dataSnapshot.children) {
                val id = reserveSnapshot.child("id").value.toString()
                //Toast.makeText(this@CustPayment, id, Toast.LENGTH_LONG).show()
                val deleteQuery = ref.child("custReservationTable").orderByChild("id").equalTo(id)
                removeData(deleteQuery)
                break
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle possible errors.
            }
        })

        Toast.makeText(applicationContext, "Cancel Reserve", Toast.LENGTH_LONG).show()
        val intent = Intent(this, CustMenu::class.java)
        startActivity(intent)
    }

    private fun removeData(query: Query){
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (reservationSnapshot in dataSnapshot.children) {
                    reservationSnapshot.ref.removeValue()

                    break
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }
}