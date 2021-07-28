package com.example.shoeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class OnboardingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_screen)
        //load a default fragment
        supportFragmentManager.beginTransaction().replace(R.id.framelayout,FragmentA()).commit()
    }
}