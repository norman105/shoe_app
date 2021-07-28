package com.example.shoeapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.activity_onboarding_screen.*
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.fragment_a.Next1
import kotlinx.android.synthetic.main.fragment_c.*


class FragmentC : Fragment() {

    lateinit var Next1: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_c, framelayout, false)

        Next1 = view.findViewById(R.id.Next1)
        Next1.setOnClickListener {
            val intent = Intent(activity, Home::class.java)
            activity?.startActivity(intent)
        }


        return view
    }
}

