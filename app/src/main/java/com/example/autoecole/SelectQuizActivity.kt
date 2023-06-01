package com.example.autoecole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SelectQuizActivity : AppCompatActivity() {
    private lateinit var SelectPlaqueBtn :Button
    private lateinit var SelectIntersectionBtn:Button
    private lateinit var SelectQstRepBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_quiz)
        SelectPlaqueBtn=findViewById(R.id.btnPlaqueQuiz)
        SelectIntersectionBtn=findViewById(R.id.btnIntersectionQuiz)
        SelectQstRepBtn=findViewById(R.id.btnQstRepQuiz)

        SelectIntersectionBtn.setOnClickListener(){
            val intent = Intent(this, IntersectionQuizActivity::class.java)
            startActivity(intent)
        }
        SelectPlaqueBtn.setOnClickListener(){
            val intent = Intent(this, PlaqueQuizActivity::class.java)
            startActivity(intent)
        }
        SelectQstRepBtn.setOnClickListener(){
            val intent = Intent(this, SelectQstRepQuizActivity::class.java)
            startActivity(intent)
        }

    }
}