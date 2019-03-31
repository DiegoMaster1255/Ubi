package com.example.mooncycle

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_setting_page.*

class SettingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_page)
        var setting = intent.getIntExtra("setting", 0)
        val names = arrayOf("Simple", "Conway", "Trig", "Trig2")
        var current = names[setting]
        currentText.text = "Aktualny algorytm: $current"


        acceptButton.setOnClickListener {
            var id = groupID.checkedRadioButtonId
            if(id!=-1) {
                when (id) {
                    R.id.simpleButton -> {
                        setting = 0
                    }
                    R.id.conwayButton -> {
                        setting = 1
                    }
                    R.id.trigButton -> {
                        setting = 2
                    }
                    R.id.trig2Button -> {
                        setting = 3
                    }
                }
                val intent = Intent(this@SettingPage, MainPage::class.java)
                intent.putExtra("setting", setting)
                startActivity(intent)
            }
        }
    }
}
