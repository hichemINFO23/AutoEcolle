package com.example.autoecole

import android.content.Intent // Importer la classe Intent pour lancer une autre activité
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class PlaqueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plaque) // Définir le layout de l'activité
    }

    // Fonction pour ouvrir l'activité PlaqueDangerActivity
    fun openDangerInterface(view: View) {
        val intent = Intent(this, PlaqueDangerActivity::class.java) // Créer un Intent avec l'activité cible
        startActivity(intent) // Lancer l'activité cible
    }

    // Fonction pour ouvrir l'activité PlaqueIndicationActivity
    fun openIndicationInterface(view: View) {
        val intent = Intent(this, PlaqueIndicationActivity::class.java) // Créer un Intent avec l'activité cible
        startActivity(intent) // Lancer l'activité cible
    }

    // Fonction pour ouvrir l'activité PlaqueObligationActivity
    fun openObligationInterface(view: View) {
        val intent = Intent(this, PlaqueObligationActivity::class.java) // Créer un Intent avec l'activité cible
        startActivity(intent) // Lancer l'activité cible
    }

    // Fonction pour ouvrir l'activité PlaqueInterdictionActivity
    fun openInterdictionInterface(view: View) {
        val intent = Intent(this, PlaqueInterdictionActivity::class.java) // Créer un Intent avec l'activité cible
        startActivity(intent) // Lancer l'activité cible
    }
}
