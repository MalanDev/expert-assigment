package com.malandev.testapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BrodcastReceivers: BroadcastReceiver() {
    override fun onReceive(p0: Context?, intent: Intent?) {
        val action = intent!!.action
        if(action != null && action == Intent.ACTION_AIRPLANE_MODE_CHANGED){
            Toast.makeText(p0,"Airplane Mode changes",Toast.LENGTH_SHORT).show()
        }
    }
}