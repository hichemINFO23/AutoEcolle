package com.example.autoecole

import androidx.appcompat.app.AppCompatActivity // Import de la classe AppCompatActivity
import android.os.Bundle // Import de la classe Bundle
import com.github.barteksc.pdfviewer.PDFView // Import de la classe PDFView de la bibliothèque PdfViewer

class IntersectionActivity : AppCompatActivity() { // Déclaration de la classe IntersectionActivity qui hérite d'AppCompatActivity
    private lateinit var pdfView: PDFView // Déclaration d'une variable pdfView de type PDFView

    override fun onCreate(savedInstanceState: Bundle?) { // Surcharge de la méthode onCreate de AppCompatActivity
        super.onCreate(savedInstanceState) // Appel de la méthode onCreate de la super classe
        setContentView(R.layout.activity_question_reponse) // Définition du layout à afficher pour l'activité
        pdfView = findViewById(R.id.pdfView) // Récupération de la vue PDFView définie dans le layout

        val assetManager = this.assets // Récupération de l'AssetManager de l'application
        val inputStream = assetManager.open("intersection.pdf") // Ouverture d'un flux de lecture sur le fichier PDF
        pdfView.fromStream(inputStream).load() // Chargement du fichier PDF dans la vue PDFView
    }
}
