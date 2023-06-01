package com.example.autoecole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LaunchActivity : AppCompatActivity() {
    private lateinit var ADMIN_BTN: Button
    private lateinit var STUDENT_BTN: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        ADMIN_BTN = findViewById(R.id.AdminBtn)
        STUDENT_BTN = findViewById(R.id.StudentBtn)
            ADMIN_BTN.setOnClickListener{
                val intent = Intent(this,LoginPageActivity::class.java)
                startActivity(intent)

            }
        STUDENT_BTN.setOnClickListener{
            val intent = Intent(this,StudentHomeActivity::class.java)
            startActivity(intent)

        }
    }
}