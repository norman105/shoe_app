package com.example.shoeapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_upload_form.*

class Home : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var BottomNav : BottomNavigationView
    lateinit var nike: ImageButton
    lateinit var puma: ImageButton
    lateinit var addidas: ImageButton
    lateinit var fila: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        BottomNav= findViewById(R.id.BottomNav)
        BottomNav.setOnNavigationItemSelectedListener(this)

        nike = findViewById(R.id.nike)
        nike.setOnClickListener {
            val intent = Intent(this@Home, ShoeActivity::class.java)
            startActivity(intent)
        }
        puma=findViewById(R.id.puma)
        puma.setOnClickListener {
            val intent = Intent(this@Home, ShoeActivity::class.java)
            startActivity(intent)
        }
        addidas=findViewById(R.id.addidas)
        addidas.setOnClickListener {
            val intent =Intent(this@Home, ShoeActivity::class.java)
            startActivity(intent)
        }
        fila=findViewById(R.id.fila)
        fila.setOnClickListener {
            val intent = Intent(this@Home,ShoeActivity::class.java)
            startActivity(intent)

        }

        
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_seller -> {
                val intent = Intent(this@Home, UploadForm::class.java)
                startActivity(intent)
            }
        }
        return true

         }
}