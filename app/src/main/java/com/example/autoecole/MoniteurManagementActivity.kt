package com.example.autoecole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MoniteurManagementActivity : AppCompatActivity() {
    private lateinit var addBtn: Button
    private lateinit var deleteBtn: Button
    private lateinit var searchBtn: Button
    private lateinit var listBtn: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moniteur_management)

        addBtn = findViewById(R.id.addBtn)
        deleteBtn = findViewById(R.id.deleteBtn)
        searchBtn = findViewById(R.id.searchBtn)
        listBtn = findViewById(R.id.listBtn)


        addBtn.setOnClickListener {
            val intent = Intent(this, AddMoniteurActivity::class.java)
            startActivity(intent)
        }

        deleteBtn.setOnClickListener {
            val intent = Intent(this, DeleteMoniteurActivity::class.java)
            startActivity(intent)
        }

        searchBtn.setOnClickListener {
            val intent = Intent(this, SearchMoniteurActivity::class.java)
            startActivity(intent)
        }
        listBtn.setOnClickListener {
            val intent = Intent(this, MoniteurListActivity::class.java)
            startActivity(intent)
        }

    }
}
