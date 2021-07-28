package com.example.shoeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_creat_account.*
import kotlinx.android.synthetic.main.activity_forgot_password.*

class CreatAccount : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var email: String= ""
    var password: String=""
    var conFirpass:String=""
    var userNAME : String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creat_account)
        //getting instance of firebase auth product
        auth = FirebaseAuth.getInstance()

        login.setOnClickListener {
            val intent = Intent(applicationContext,UploadForm::class.java)
            startActivity(intent)
        }
        forgotpassword.setOnClickListener {
                val intent = Intent(applicationContext, ForgotPassword::class.java)
                startActivity(intent)
        }
        button3.setOnClickListener {
            captureInput()
        }
    }

    private fun captureInput() {
        email = enteremail.text.toString()
        password = passwords.text.toString()
        conFirpass = confirmpassword.text.toString()
        userNAME = username.text.toString()



        if (!password.equals(conFirpass) && email.isEmpty() && password.isEmpty()) {
            Toast.makeText(
                applicationContext,
                "Pasword do not match or field cannot be empty",
                Toast.LENGTH_SHORT
            ).show()
        }else {
            registerToFirebase(email, password)
        }
        }

    private fun registerToFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(applicationContext, "Account ncreated", Toast.LENGTH_SHORT).show()
                    Log.d("auth","details are " + it.exception)
                    updateUi()

                }else{
                    Toast.makeText(applicationContext, "Account not created try again", Toast.LENGTH_SHORT).show()
                    Log.d("auth","details are " + it.exception)

            }

    }
}

    private fun updateUi() {
        val intent = Intent(applicationContext, Login::class.java)
        startActivity(intent)
    }
}