package com.example.autoecole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LessonActivity : AppCompatActivity() {
    private lateinit var PlaqueBtn:Button
    private lateinit var IntersectionBtn:Button
    private lateinit var QuestionRepBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        PlaqueBtn=findViewById(R.id.btnPlaque)
        IntersectionBtn=findViewById(R.id.btnIntersection)
        QuestionRepBtn=findViewById(R.id.btnQuestionRep)

        PlaqueBtn.setOnClickListener(){
            val intent = Intent(this,PlaqueActivity::class.java)
            startActivity(intent)

        }
        IntersectionBtn.setOnClickListener(){
            val intent = Intent(this,IntersectionActivity::class.java)
            startActivity(intent)

        }
        QuestionRepBtn.setOnClickListener(){
            val intent = Intent(this,QuestionReponseActivity::class.java)
            startActivity(intent)

        }
    }
}