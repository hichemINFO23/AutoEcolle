package com.example.autoecole


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.autoecole.databinding.ActivityStudentHomeBinding
import com.example.autoecole.global.DB
import com.example.autoecole.manager.SessionManager


class StudentHomeActivity : AppCompatActivity() {
    private lateinit var aboutBtn: Button
    private lateinit var lessonBtn: Button
    private lateinit var drivingBtn: Button
    private lateinit var safetyBtn: Button
    private lateinit var testBtn: Button
    private lateinit var notouchBtn: Button
    var session: SessionManager? = null
    var db: DB? = null

    lateinit var binding: ActivityStudentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DB(this)
        session = SessionManager(this)
        aboutBtn = findViewById(R.id.generalBtn)
        lessonBtn = findViewById(R.id.lessonBtn)
        drivingBtn = findViewById(R.id.lessonPraticBtn)
        safetyBtn = findViewById(R.id.safeBtn)
        testBtn = findViewById(R.id.testBtn)
        notouchBtn = findViewById(R.id.brossiBtn)


        aboutBtn.setOnClickListener() {
            val intent = Intent(this, MainHomeStudentActivity::class.java)
            startActivity(intent)
        }
        lessonBtn.setOnClickListener() {
            val intent = Intent(this, LessonActivity::class.java)
            startActivity(intent)
        }
        drivingBtn.setOnClickListener() {
            val intent = Intent(this, PraticActivity::class.java)
            startActivity(intent)
        }
        safetyBtn.setOnClickListener() {
            val intent = Intent(this, SafeActivity::class.java)
            startActivity(intent)
        }
        testBtn.setOnClickListener() {
            val intent = Intent(this, SelectQuizActivity::class.java)
            startActivity(intent)
        }
        notouchBtn.setOnClickListener() {
            val intent = Intent(this, ViolationActivity::class.java)
            startActivity(intent)
        }


    }
}