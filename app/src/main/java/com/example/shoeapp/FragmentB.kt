package com.example.shoeapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.activity_onboarding_screen.*

    
class FragmentB : Fragment() {
    lateinit var Skip1: Button
    lateinit var Next1: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_b,framelayout, false)
        Skip1 =view.findViewById(R.id.Skip1)
        Next1 = view.findViewById(R.id.Next1)

        Skip1.setOnClickListener {
            val intent = Intent(activity, Home::class.java)
            activity?.startActivity(intent)
        }
        Next1.setOnClickListener {
            val transaction =activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.framelayout,FragmentC())
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }

        return  view
    }


}