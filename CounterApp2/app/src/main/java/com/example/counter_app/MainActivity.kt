package com.example.counter_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var counterVal: TextView
    private lateinit var startCounter: Button
    private lateinit var stopCounter: Button
    var countervalue: Int = 0
    var timer= MyCounter(1000000,1000)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterVal=findViewById(R.id.counter_value)
        startCounter=findViewById(R.id.start_button)
        stopCounter=findViewById(R.id.stop_button)

        startCounter.setOnClickListener {
            timer.start()
            startCounter.isEnabled= false
        }

        stopCounter.setOnClickListener {
            timer.cancel()
            startCounter.isEnabled= true
        }
    }

    inner class MyCounter(MillisInFuture:Long, CountDownInterval:Long) : CountDownTimer(MillisInFuture, CountDownInterval){
        override fun onFinish() {
            TODO("Not yet implemented")
        }

        override fun onTick(millisUntilFinished: Long) {
            countervalue++
            counterVal.text = (countervalue).toString()
        }
    }

}