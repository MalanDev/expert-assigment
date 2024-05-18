package com.malandev.testapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EventHandlinActivity : AppCompatActivity() {


    private lateinit var tapButton: Button
    private lateinit var mainView: ConstraintLayout
    private lateinit var velocity1Text: TextView
    private lateinit var velocity2Text: TextView
    private lateinit var counterText: TextView

    private lateinit var speechButton: Button

    private lateinit var hideButton: Button
    private lateinit var showButton: Button

    private var count = 1

    private val REQUEST_CODE = 899

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_event_handlin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       declareVariables()
        declareEvents()
        toAccelometer()



    }


  private fun  declareVariables (){
      tapButton = findViewById(R.id.btnTap)
      mainView = findViewById(R.id.main)
      velocity1Text = findViewById(R.id.txtVelocity1)
      velocity2Text = findViewById(R.id.txtVelocity2)
      counterText = findViewById(R.id.txtCounter)
      speechButton = findViewById(R.id.btnSpeech)
      hideButton = findViewById(R.id.btnHide)
      showButton = findViewById(R.id.btnShow)

  }

    @SuppressLint("ClickableViewAccessibility")
    private fun declareEvents(){

        tapButton.setOnClickListener {
            count++
            counterText.text = count.toString()
            Toast.makeText(this,"Tap Clicked!",Toast.LENGTH_SHORT).show()

        }


        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                // Handle fling gesture
                velocity1Text.text = e1?.y.toString()
                velocity2Text.text = velocityY.toString()
                return super.onFling(e1, e2, velocityX, velocityY)
            }
        })


        mainView.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }


        speechButton.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something")
            startActivityForResult(intent, REQUEST_CODE)
        }


        hideButton.setOnClickListener {
            hideKeyboard()
        }

        showButton.setOnClickListener {
            showKeyboard()
        }


    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val spokenText = results?.get(0)
            Toast.makeText(this,spokenText,Toast.LENGTH_LONG).show()

        }

    }

    private fun toAccelometer(){
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                // Handle accelerometer data

            }
            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        }
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)

    }

    private fun hideKeyboard() {
        val view: View? = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }


    }

    private fun showKeyboard() {
        val view: View? = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
        }


    }

}