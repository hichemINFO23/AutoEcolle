package com.example.autoecole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainPlanningActivity : AppCompatActivity() {
    private lateinit var listBtn:Button
    private lateinit var fixBtn:Button
    private lateinit var deleteBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_planning)
        listBtn=findViewById(R.id.listBtn)
        fixBtn=findViewById(R.id.fixBtn)
        deleteBtn=findViewById(R.id.deleteBtn)
        listBtn.setOnClickListener(){
            val intent = Intent(this, PlanningActivity::class.java)
            startActivity(intent)
        }
            fixBtn.setOnClickListener(){
                val intent = Intent(this, FixSessionActivity::class.java)
                startActivity(intent)
            }
            deleteBtn.setOnClickListener(){
                val intent = Intent(this, DeleteSessionActivity::class.java)
                startActivity(intent)
            }

    }
}