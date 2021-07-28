package com.example.shoeapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_upload_form.*
import java.io.IOException
import java.util.*

class UploadForm : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val PIC_IMAGE_REQUEST = 71
    lateinit var productImage: Uri
    var productName: String = ""
    var productPrice: String = ""
    var productContactPhone: String = ""

    //tags for db and storage
    private var firebaseStorage: FirebaseStorage? = null
    private var storageRef: StorageReference? = null
    private var databaseRef: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_form)
        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()

        val shoe_brands = resources.getStringArray(R.array.Shoe_brands)
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, shoe_brands)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Toast.makeText(this@UploadForm, getString(R.string.selected_item) + "" +
                            "" + shoe_brands[position], Toast.LENGTH_SHORT).show()

                }

            }
        }
        button5.setOnClickListener {
            auth.signOut()
            updateUI()
        }
        imageView3.setOnClickListener {
            val intent = Intent()
            intent.type = "image/"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                    Intent.createChooser(intent, "Select Picture"), PIC_IMAGE_REQUEST
            )
        }
        view.setOnClickListener {
            productPrice = retailprices.text.toString()
            productContactPhone = contactnumbers.text.toString()
            productName = names.text.toString()

            if (productName.isEmpty() && productPrice.isEmpty() && productContactPhone.isEmpty() && productName.isEmpty()) {
                Toast.makeText(applicationContext, "Field should not be emmpty", Toast.LENGTH_SHORT).show()
            } else {
                submitToFirebase(productName, productPrice, productContactPhone, productName)
            }


        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PIC_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            //check if image is picked
            if (data == null || data.data == null) {
                return
            }
            productImage = data.data!!
            //showing user image
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, productImage)
                imageView3.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun submitToFirebase(productName: String, productPrice: String, productContactPhone: String, productName1: String) {
        //process to take database
        ///uuid  ref to image name
        val ref = storageRef?.child("shoesImage/" + UUID.randomUUID().toString())
        //put the image to storage bucket
        val uploadTask = ref?.putFile(productImage!!)
        //monitoring the process
        val urlTask= uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>>
        {
           if (!it.isSuccessful){
               it.exception?.let {
                   throw it
               }
           }
            return@Continuation ref.downloadUrl

        })?.addOnCompleteListener {
            if (it.isSuccessful) {
                //hecking if upload process is complete ,if complete save
                //download url for image
                val downloadUri = it.result
                Log.d("image","download url" + downloadUri.toString())
                //beginin the process of taking of data to realtime database
                //generate id for ref
                val shoeId = databaseRef?.push()?.key
                //hold this data in our model
                val shoeUpload =shoeId.let {
                    ProductModel(shoeId.toString(),productName,productPrice,productContactPhone,
                        productContactPhone,downloadUri.toString())
                }
                //sending values to realtime database
                if (shoeId != null) {
                    databaseRef?.child(shoeId)?.setValue(shoeUpload)
                        ?.addOnCompleteListener {
                            Toast.makeText(applicationContext, "Shoe added successfully", Toast.LENGTH_SHORT).show()

                        }?.addOnFailureListener {
                            Toast.makeText(applicationContext, "Error check the internet", Toast.LENGTH_SHORT).show()
                        }
                    //end of sending data to real time data
                }
            }else{
                Toast.makeText(applicationContext, "Error occured,check internet connection", Toast.LENGTH_SHORT).show()
            }

        }?.addOnFailureListener {
            //here u can get actual error from fire base
            val massageError = it.message
            Toast.makeText(applicationContext, "Error is", Toast.LENGTH_SHORT).show()

        }

    }

    private fun updateUI() {
        val intent = Intent(applicationContext, Login::class.java)
        startActivity(intent)
    }


    fun saveData(view: View) {
        //capture user data
        val retailpricess = retailprices.text.toString()
        val contactnumberss = contactnumbers.text.toString()
        val name = names.text.toString()
        val databaseHelper = DatabaseHelper(this)
        //validate
        if (retailpricess.trim() != "" && contactnumberss.trim() != "" && name.trim() != "") {
            val status = databaseHelper.addSeller(SqlliteModel(retailpricess, contactnumberss, name))
            if (status > -1) {
                Toast.makeText(applicationContext, "Record submitted", Toast.LENGTH_LONG).show()
                retailprices.text?.clear()
                contactnumbers.text?.clear()
                names.text?.clear()
            } else {
                Toast.makeText(applicationContext, "Something went wrong,try again ", Toast.LENGTH_LONG)
                        .show()
            }
        }
    }
}
       // fun viewData(view: View) {
          //  val databaseHelper = DatabaseHelper(this)
            //make ref to the read data method
            //val viewData: List<SqlliteModel> = databaseHelper.readData()
            //define array variables to store each record
           // val arrayRetailprrice = Array<String>(viewData.size) { "null" }
           // val arrayContactnumber = Array<String>(viewData.size) { "0" }
           // val arrayname = Array<String>(viewData.size) { "null" }
            //looping our record to save in variable above
           // var index = 0
           // for (e in viewData) {
            //    arrayRetailprrice[index] = e.retailprices.toString()
              //  arrayContactnumber[index] = e.contactnumbers.toString()
               // arrayname[index] = e.names.toString()
                //index++
           // }
          //  val myadapter = ShoeAppAdapter(this, arrayRetailprrice, arrayContactnumber, arrayname)
          //  listview.adapter = myadapter
        //}
   // }












