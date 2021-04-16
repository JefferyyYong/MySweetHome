package com.example.mysweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    //private lateinit var mAuth: FirebaseAuth
    //private lateinit var emailEt: EditText
    //private lateinit var passwordEt: EditText
    //private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

       // emailEt = findViewById(R.id.login_email)
       // passwordEt = findViewById(R.id.login_password)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        //mAuth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {
            val intent = Intent(this, FrontDesk::class.java)
        startActivity(intent)
        }
        /*loginBtn.setOnClickListener {
            var email: String = emailEt.text.toString()
            var password: String = passwordEt.text.toString()
            val sample1 = "jy97@gmail.com"
            val psample2 = "123456"
            val adminSample ="cch97@gmail.com"


            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this@Login, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else{
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, FrontDesk::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        //staff login
                        if (email == sample1 && password == psample2) {
                            Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, FrontDesk::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            if (email == adminSample && password == psample2) {
                                Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG)
                                    .show()
                                val intent = Intent(this, AdminMenu::class.java)
                                startActivity(intent)
                                finish()
                            }else{
                            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                        }
                    }
                    }
                })
            }
        }*/

    }

}