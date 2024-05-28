package com.malandev.testapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {

    lateinit var txtDashboard: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       declareVariables();
        sharedPreferences()
    }

    private fun declareVariables(){
        txtDashboard = findViewById(R.id.txtDashboard)

        val userName = intent.getStringExtra("userName")
        val password = intent.getStringExtra("password")

        txtDashboard.text = userName
    }


    private fun sharedPreferences(){
        val sharedPref = getSharedPreferences("User", Context.MODE_PRIVATE)

        sharedPref.edit {
            putString("userName",txtDashboard.text.toString())
            apply()
        }

       val userName =  sharedPref.getString("userName","")

    }
}