package com.example.shoeapp

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ShoeAppAdapter (private val context: Activity,
    private val retailprice:Array<String>,private val contactnumber:Array<String>, private val name:Array<String>)
    :ArrayAdapter<String>(context,R.layout.custom,name){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = context.layoutInflater
            val rovview = inflater.inflate(R.layout.custom, null, true)
            //view  identification

            val textRetailprice: TextView =rovview.findViewById(R.id.sellerretailprice)
            val textContactnumber: TextView =rovview.findViewById(R.id.sellercontactnumber)
            val textName: TextView =rovview.findViewById(R.id.sellername)

            textRetailprice.text ="RETAILPRICE:${retailprice[position]}"
            textContactnumber.text ="CONTACTNUMBER:${contactnumber[position]}"
            textName.text ="CONTACTNAME:${name[position]}"

            return rovview
    }

    }
