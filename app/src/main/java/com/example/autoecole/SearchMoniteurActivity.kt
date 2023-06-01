package com.example.autoecole

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.autoecole.global.DB

class SearchMoniteurActivity : AppCompatActivity() {
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var db: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_moniteur)

        // Récupérer les références des vues à partir de leurs ID
        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)
        resultTextView = findViewById(R.id.resultTextView)
        db = DB(this)

        // Ajouter un écouteur de texte pour le champ de recherche
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val searchTerm = s.toString().trim()
                // Activer ou désactiver le bouton de recherche en fonction du contenu du champ de recherche
                if (searchTerm.isNotEmpty()) {
                    searchButton.isEnabled = true
                } else {
                    searchButton.isEnabled = false
                }
            }
        })

        // Ajouter un écouteur de clic pour le bouton de recherche
        searchButton.setOnClickListener {
            val searchTerm = searchEditText.text.toString().trim()
            searchMoniteur(searchTerm)
        }
    }

    private fun searchMoniteur(searchTerm: String) {
        // Construire la requête SQL pour rechercher des moniteurs selon le terme de recherche
        val query =
            "SELECT * FROM MONITEUR WHERE FNAME LIKE '%$searchTerm%' OR SNAME LIKE '%$searchTerm%'"
        val cursor = db.fireQuery(query)

        val resultBuilder = StringBuilder()
        if (cursor != null && cursor.moveToFirst()) {
            // Parcourir les résultats de la requête et construire une chaîne de résultat
            do {
                val id = cursor.getInt(cursor.getColumnIndex("ID"))
                val firstName = cursor.getString(cursor.getColumnIndex("FNAME"))
                val lastName = cursor.getString(cursor.getColumnIndex("SNAME"))
                val birthDate = cursor.getString(cursor.getColumnIndex("BIRTHDATE"))
                val nation = cursor.getString(cursor.getColumnIndex("NATION"))
                val address = cursor.getString(cursor.getColumnIndex("ADRESSE"))
                val phoneNumber = cursor.getString(cursor.getColumnIndex("PHONENUMBER"))
                val email = cursor.getString(cursor.getColumnIndex("EMAIL"))
                val matricule = cursor.getInt(cursor.getColumnIndex("MATRICULE"))
                val marque = cursor.getString(cursor.getColumnIndex("MARQUE"))
                val type = cursor.getString(cursor.getColumnIndex("TYPE"))

                // Ajouter les informations du moniteur à la chaîne de résultat
                resultBuilder.append(" الرقم  : $id\n")
                resultBuilder.append(" الاسم : $firstName\n")
                resultBuilder.append("اللقب : $lastName\n")
                resultBuilder.append("تاريخ الميلاد : $birthDate\n")
                resultBuilder.append("الجنسية : $nation\n")
                resultBuilder.append("العنوان : $address\n")
                resultBuilder.append(" رقم الهاتف : $phoneNumber\n")
                resultBuilder.append("  البريد الالكتروني : $email\n")
                resultBuilder.append("\n")
                resultBuilder.append("رقم تسجيل السيارة : $matricule\n")
                resultBuilder.append("شعار السيارة : $marque\n")
                resultBuilder.append("نوع السيارة : $type\n")
                resultBuilder.append("__________________________________________________________\n")
            } while (cursor.moveToNext())

            cursor.close()
        } else {
            resultBuilder.append("لا توجد نتيجة...")
        }

        // Afficher la chaîne de résultat dans le TextView
        resultTextView.text = resultBuilder.toString()
    }
}
