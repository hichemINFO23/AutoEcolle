package com.example.autoecole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SelectQstRepQuizActivity : AppCompatActivity() {
    private lateinit var SelectB :Button
    private lateinit var SelectC:Button
    private lateinit var SelectD:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_qst_rep_quiz)
        SelectB=findViewById(R.id.btnQB)
        SelectC=findViewById(R.id.btnQC)
        SelectD=findViewById(R.id.btnQD)

        SelectB.setOnClickListener(){
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        SelectC.setOnClickListener(){
            val intent = Intent(this, QuizCEActivity::class.java)
            startActivity(intent)
        }
        SelectD.setOnClickListener(){
            val intent = Intent(this, QuizDActivity::class.java)
            startActivity(intent)
        }

    }
}