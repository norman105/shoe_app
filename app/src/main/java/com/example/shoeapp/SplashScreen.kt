package com.example.shoeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //using handler to delay screen
        Handler().postDelayed({
            val intents =Intent (this,OnboardingScreen::class.java)
            startActivity(intents)
        },2000)//screen delay for 2seconnds


    }
}
