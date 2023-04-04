package com.example.firstapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences
    private val keyForNumber: String = "key_for_number"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = this.getSharedPreferences("myFile", MODE_PRIVATE)

        val myButton = findViewById<Button>(R.id.plusOneButton)
        val myText = findViewById<TextView>(R.id.myTextView)
        val minusButton = findViewById<Button>(R.id.minus_Button)
        val resetButton = findViewById<Button>(R.id.reset)

        var counter = getNumber()
        myText.text = counter.toString()

        myButton.setOnClickListener {
            counter += 1
            saveNumber(counter)
            val numberAsString = counter.toString()
            myText.text = numberAsString

        }

        minusButton.setOnClickListener {
            counter -= 1
            saveNumber(counter)
            val numberAsString = counter.toString()
            myText.text = numberAsString
        }

        resetButton.setOnClickListener {
            counter = 0
            saveNumber(counter)
            val numberAsString = counter.toString()
            myText.text = numberAsString
        }


    }

    private fun saveNumber(number: Int) {
        val editor = sharedPref.edit()
        editor.putInt(keyForNumber, number)
        editor.commit()
    }

    private fun getNumber(): Int {
        return sharedPref.getInt(keyForNumber, 0)
    }

}