package com.example.mooncycle

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.floor
import java.util.*

class MainPage : AppCompatActivity() {

    var setting = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setting = intent.getIntExtra("setting", 2)
        var calendar = Calendar.getInstance();
        nsetInfo(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        //setInfo(2022, 7, 11)
        settingsButton.setOnClickListener {
            val intent = Intent(this@MainPage, SettingPage::class.java)
            intent.putExtra("setting", setting)
            startActivity(intent)
        }
        fullMoonButton.setOnClickListener{
            val intent = Intent(this@MainPage, FullMoonsPage::class.java)
            intent.putExtra("setting", setting)
            startActivity(intent)
        }
    }

    fun nsetInfo(year: Int, month: Int, day: Int){
        val worker = Worker(setting)
        var temp = worker.getPhase(year, month, day)
        var temp_cal = Calendar.getInstance();
        temp_cal.set(Calendar.YEAR, year)
        temp_cal.set(Calendar.MONTH, month)
        temp_cal.set(Calendar.DAY_OF_MONTH, day)
        var percent = 0.00
        if(temp < 15){
            percent = temp.toDouble()/15 * 10;
            var result = percent.toInt()
            if(percent >= 25 && percent < 75){
                moonImage.setImageResource(R.drawable.first_quarter)
            }
            todayPhase.text = "Dzisiaj: $result%"

            temp_cal.add(Calendar.DAY_OF_YEAR, temp*-1)
            var day = temp_cal.get(Calendar.DAY_OF_MONTH)
            var month = temp_cal.get(Calendar.MONTH) + 1
            var year = temp_cal.get(Calendar.YEAR)
            lastNewMoon.text = "Poprzedni nów: $day.$month.$year r."

            temp_cal.add(Calendar.DAY_OF_YEAR, 15)
            day = temp_cal.get(Calendar.DAY_OF_MONTH)
            month = temp_cal.get(Calendar.MONTH) + 1
            year = temp_cal.get(Calendar.YEAR)
            nextFullMoon.text = "Następna pełnia: $day.$month.$year r."
        }
        else {
            percent = (1 - (temp.toDouble() - 15) / 15) * 100

            var result = percent.toInt()
            if(percent >= 25 && percent < 75){
                moonImage.setImageResource(R.drawable.third_quarter)
            }
            todayPhase.text = "Dzisiaj:$temp $result%"

            temp_cal.add(Calendar.DAY_OF_YEAR, 29 - temp + 1)
            var day = temp_cal.get(Calendar.DAY_OF_MONTH)
            var month = temp_cal.get(Calendar.MONTH) + 1
            var year = temp_cal.get(Calendar.YEAR)
            lastNewMoon.text = "Następny nów: $day.$month.$year r."

            temp_cal.add(Calendar.DAY_OF_YEAR, -15)
            day = temp_cal.get(Calendar.DAY_OF_MONTH)
            month = temp_cal.get(Calendar.MONTH) + 1
            year = temp_cal.get(Calendar.YEAR)
            nextFullMoon.text = "Poprzednia pełnia: $day.$month.$year r."
        }
        if(percent < 25){
            moonImage.setImageResource(R.drawable.new_moon)
        }
        else if(percent > 75){
            moonImage.setImageResource(R.drawable.full_moon)
        }
    }

}
