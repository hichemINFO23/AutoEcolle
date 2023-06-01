package com.example.autoecole

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.autoecole.R
import com.github.barteksc.pdfviewer.PDFView

class ViolationActivity : AppCompatActivity() {
    private lateinit var pdfView: PDFView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plaque_danger)
        pdfView = findViewById(R.id.pdfView)

        val assetManager = this.assets
        val inputStream = assetManager.open("violation.pdf")
        pdfView.fromStream(inputStream).load()
    }
}