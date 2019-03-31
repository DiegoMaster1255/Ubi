package com.example.mooncycle

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_full_moons_page.*
import java.util.*

class FullMoonsPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_moons_page)
        var setting = intent.getIntExtra("setting", 0)
        addButton.setOnClickListener {
            var temp = yearInput.text.toString().toInt()
            temp += 1
            yearInput.setText("$temp")
        }
        subButton.setOnClickListener {
            var temp = yearInput.text.toString().toInt()
            temp -= 1
            yearInput.setText("$temp")
        }
        backButton.setOnClickListener {
            val intent = Intent(this@FullMoonsPage, MainPage::class.java)
            intent.putExtra("setting", setting)
            startActivity(intent)
        }
        confirmButton.setOnClickListener {
            var year = yearInput.text.toString().toInt()
            var calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, 1)
            calendar.set(Calendar.DAY_OF_MONTH, 1)
            val worker = Worker(setting)
            var day = worker.getPhase(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            //calendar.add(Calendar.DAY_OF_MONTH, day)
           // textView9.setText("$day")
            var diff = 15 - day
            //textView10.setText("$diff")
            calendar.add(Calendar.DAY_OF_MONTH, diff)
            day += diff
            if (diff < 0){
                calendar.add(Calendar.DAY_OF_MONTH, 29)
            }
            //textView11.setText("$day")
            setTextView(textView, calendar)
            calendar.add(Calendar.DAY_OF_MONTH, 29)
            setTextView(textView2, calendar)
            calendar.add(Calendar.DAY_OF_MONTH, 29)
            setTextView(textView3, calendar)
            calendar.add(Calendar.DAY_OF_MONTH, 29)
            setTextView(textView4, calendar)
            calendar.add(Calendar.DAY_OF_MONTH, 29)
            setTextView(textView5, calendar)
            calendar.add(Calendar.DAY_OF_MONTH, 29)
            setTextView(textView6, calendar)
            calendar.add(Calendar.DAY_OF_MONTH, 29)
            setTextView(textView7, calendar)
            calendar.add(Calendar.DAY_OF_MONTH, 29)
            setTextView(textView8, calendar)
            calendar.add(Calendar.DAY_OF_MONTH, 29)
            setTextView(textView9, calendar)
            calendar.add(Calendar.DAY_OF_MONTH, 29)
            setTextView(textView10, calendar)
            calendar.add(Calendar.DAY_OF_MONTH, 29)
            setTextView(textView11, calendar)
            calendar.add(Calendar.DAY_OF_MONTH, 29)
            setTextView(textView12, calendar)
        }
    }

    fun setTextView(textView : TextView, calendar : Calendar){
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        var month = calendar.get(Calendar.MONTH)
        var year = calendar.get(Calendar.YEAR)
        if(month == 0){
            month = 12
            year -= 1
        }
        textView.setText("$day.$month.$year")
    }


}
