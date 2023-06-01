package com.example.autoecole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class ManagementPersonActivity : AppCompatActivity() {
    private lateinit var StudentBtn: Button
    private lateinit var MoniteurBtn: Button





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management_person)

        MoniteurBtn = findViewById(R.id.MoniteurBtn)
        StudentBtn = findViewById(R.id.StudentBtn)



        MoniteurBtn.setOnClickListener {
            val intent = Intent(this, MoniteurManagementActivity::class.java)
            startActivity(intent)
        }

        StudentBtn.setOnClickListener {
            val intent = Intent(this, StudentManagementActivity::class.java)
            startActivity(intent)
        }


    }
}
