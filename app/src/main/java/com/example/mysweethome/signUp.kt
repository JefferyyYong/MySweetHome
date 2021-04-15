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

class signUp : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth


    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    private lateinit var signUpBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        mAuth = FirebaseAuth.getInstance()

        emailEt = findViewById(R.id.email_edt_text)
        passwordEt = findViewById(R.id.pass_edt_text)
        signUpBtn = findViewById(R.id.signup_btn)

        val signUpForm = findViewById<Button>(R.id.signup_btn)
        //back button
        val actionbar = supportActionBar
        //back button
            actionbar!!.title = "Sign up"
            actionbar.setDisplayHomeAsUpEnabled(true)

        signUpForm.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        signUpBtn.setOnClickListener{
            var email: String = emailEt.text.toString()
            var password: String = passwordEt.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else{
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

    }
    //back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}