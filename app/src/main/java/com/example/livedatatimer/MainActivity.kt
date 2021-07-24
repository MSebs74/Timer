package com.example.livedatatimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        //viewModel.startTimer()

        viewModel.seconds().observe(this, Observer {
            tvTimer.text = it.toString()
        })

        viewModel.finish().observe(this, Observer {
            if (it){
                Toast.makeText(this, "Finished", Toast.LENGTH_LONG).show()
                btStart.isClickable = true

            }
        })

        btStart.setOnClickListener {
            if (editTextNumber.text.isEmpty() ){
                Toast.makeText(this, "Input seconds before clicking start !", Toast.LENGTH_LONG).show()

            } else {
                btStart.isClickable = false

                viewModel.timerValue.value = editTextNumber.text.toString().toLong()*1000
                viewModel.startTimer()

            }
        }

        btStop.setOnClickListener {
            if (editTextNumber.text.isEmpty()){
                Toast.makeText(this, "Input seconds before clicking stop !", Toast.LENGTH_LONG).show()
            }else{
                tvTimer.text = "0"
                btStart.isClickable = true
                viewModel.stopTimer()
            }
        }
    }
}