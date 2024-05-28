package com.malandev.testapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignInScreen2 : AppCompatActivity() {

    private lateinit var txtUserName: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnSubmit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in_screen2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        declareVariables()
        declareEvents()
    }


    private fun declareVariables() {
      txtUserName = findViewById(R.id.txtUserName)
      txtPassword = findViewById(R.id.txtPassword)
      btnSubmit = findViewById(R.id.btnSubmit)

    }

    private fun declareEvents() {

       btnSubmit.setOnClickListener {
           val userName = txtUserName.text.toString()
           val password = txtPassword.text.toString()

           if(userName == "malan" && password == "1234"){
               Toast.makeText(this,"Login!",Toast.LENGTH_LONG).show()
               // Explicit Intent
               val intent = Intent(this,DashboardActivity::class.java)
               startActivity(intent)

               //Implicit Intent
               val impIntent = Intent(Intent.ACTION_VIEW)
               impIntent.data = Uri.parse("https://www.google.com/")
               startActivity(impIntent)

           }else{
               Toast.makeText(this,"UserName and password wrong!",Toast.LENGTH_LONG).show()
           }
       }
    }

}