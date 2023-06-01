package com.example.autoecole

import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.autoecole.global.DB

class DeleteSessionActivity : AppCompatActivity() {
    private lateinit var sessionSpinner: Spinner
    private lateinit var deleteButton: Button

    private lateinit var sessionList: MutableList<String>
    private lateinit var sessionIdList: MutableList<Int>
    private lateinit var selectedSessionId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_session)

        sessionSpinner = findViewById(R.id.sessionSpinner) // Référence au Spinner dans la mise en page
        deleteButton = findViewById(R.id.deleteButton) // Référence au bouton de suppression dans la mise en page

        val db = DB(this) // Crée une instance de la classe d'aide à la base de données

        // Récupère toutes les sessions depuis la base de données
        val sessionCursor: Cursor? = db.fireQuery("SELECT * FROM SESSION")

        sessionCursor?.let { cursor ->
            sessionList = mutableListOf()
            sessionIdList = mutableListOf()

            if (cursor.moveToFirst()) {
                do {
                    val sessionId = cursor.getInt(cursor.getColumnIndex("ID"))

                    // Construit les détails de la session sous forme de chaîne de caractères
                    val sessionDetails = "رقم الحصة: ${cursor.getInt(cursor.getColumnIndex("ID"))}\n" +
                            "رقم الطالب : ${cursor.getInt(cursor.getColumnIndex("STUDENT_ID"))}\n" +
                            "التاريخ: ${cursor.getString(cursor.getColumnIndex("DATE"))}\n" +
                            "الساعة: ${cursor.getString(cursor.getColumnIndex("TIME"))}\n" +
                            "رقم المدرب: ${cursor.getInt(cursor.getColumnIndex("MONITEUR_ID"))}\n" +
                            "المكان: ${cursor.getString(cursor.getColumnIndex("PLACE"))}"

                    // Ajoute les détails de la session à la liste des sessions et à la liste des IDs des sessions
                    sessionList.add(sessionDetails)
                    sessionIdList.add(sessionId)
                } while (cursor.moveToNext())
            }

            // Crée un adaptateur ArrayAdapter pour afficher les sessions dans le Spinner
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sessionList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // Associe l'adaptateur au Spinner
            sessionSpinner.adapter = adapter
        }

        sessionCursor?.close()
        db.close()

        // Écouteur de sélection d'élément du Spinner
        sessionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedSessionId = sessionIdList[position].toString() // Récupère l'ID de la session sélectionnée
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Faites quelque chose si aucun élément n'est sélectionné
            }
        }

        // Écouteur de clic du bouton de suppression
        deleteButton.setOnClickListener {
            val db = DB(this) // Crée une nouvelle instance de la classe d'aide à la base de données
            val deleteQuery = "DELETE FROM SESSION WHERE ID = $selectedSessionId" // Requête SQL pour supprimer la session avec l'ID sélectionné
            val success = db.executeQuery(deleteQuery) // Exécute la requête de suppression dans la base de données

            if (success) {
                Toast.makeText(this, "تم الحذف بنجاح", Toast.LENGTH_SHORT).show() // Affiche un message de succès si la suppression est réussie
            } else {
                Toast.makeText(this, "فشل أثناء الحذف", Toast.LENGTH_SHORT).show() // Affiche un message d'échec si la suppression échoue
            }

            db.close() // Ferme la connexion à la base de données
        }
    }
}
