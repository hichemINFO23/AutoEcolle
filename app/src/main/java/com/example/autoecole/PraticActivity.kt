package com.example.autoecole

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView

class PraticActivity : AppCompatActivity() {
    private lateinit var pdfView: PDFView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_reponse)
        pdfView = findViewById(R.id.pdfView)

        val assetManager = this.assets
        val inputStream = assetManager.open("conduite.pdf")
        pdfView.fromStream(inputStream).load()
    }
}