package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        emailEt = findViewById(R.id.login_email)
        passwordEt = findViewById(R.id.login_password)
        val staff = findViewById<TextView>(R.id.staffN)
        val staff1 = findViewById<TextView>(R.id.staff1)
        val staff2 = findViewById<TextView>(R.id.staff2)
        val staff3 = findViewById<TextView>(R.id.staff3)

        loginBtn = findViewById(R.id.loginBtn)
        mAuth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {
            var email: String = emailEt.text.toString()
            var password: String = passwordEt.text.toString()
            val sample1 = "jy97@gmail.com"
            val psample2 = "123456"
            val adminSample ="christal@gmail.com"
            val staffSample ="john@gmail.com"
            val staffSample2 ="alice@gmail.com"
            val staffSample3 ="apple@gmail.com"


            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this@Login, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else{
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if(task.isSuccessful) {
                        var x = email   //test
                        Toast.makeText(this, "You Have Successfully Logged In", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, CustMenu::class.java)
                        intent.putExtra("CustomerEmail",x) //test
                        startActivity(intent)
                        finish()
                    }else {
                        //staff login
                        if (email == sample1 && password == psample2) {
                            var x = "Jeffery"
                            Toast.makeText(this, "You Have Successfully Logged In", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, FrontDesk::class.java)
                            intent.putExtra("FrontDeskStaff",x)
                            startActivity(intent)
                            finish()

                        } else {
                            if (email == adminSample && password == psample2) {
                                var x = "Christal"
                                Toast.makeText(this, "You Have Successfully Logged In", Toast.LENGTH_LONG)
                                        .show()
                                val intent = Intent(this, AdminMenu::class.java)
                                intent.putExtra("AdminStaff",x)
                                startActivity(intent)
                                finish()
                            } else {
                                if (email == staffSample && password == psample2) {
                                    var x = "John"
                                    Toast.makeText(this, "You Have Successfully Logged In", Toast.LENGTH_LONG).show()
                                    val intent = Intent(this, HkStaffMenu::class.java)
                                    intent.putExtra("HKS1",x)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    if (email == staffSample2 && password == psample2) {
                                        var x = "Alice"
                                        Toast.makeText(this, "You Have Successfully Logged In", Toast.LENGTH_LONG).show()
                                        val intent = Intent(this, HkStaffMenu::class.java)
                                        intent.putExtra("HKS1",x)
                                        startActivity(intent)
                                        finish()
                                    } else {
                                        var hk1 : String =  staffSample3
                                        if (email == staffSample3 && password == psample2) {
                                            var x = "Apple"
                                            Toast.makeText(this, "You Have Successfully Logged In", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, HkStaffMenu::class.java)
                                            intent.putExtra("HKS1",x)
                                            startActivity(intent)
                                            finish()
                                        } else {
                                            Toast.makeText(this, "Login Failed. Email Does Not Exist", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                }
                            }
                        }
                    }
                })
            }
        }
    }
}
