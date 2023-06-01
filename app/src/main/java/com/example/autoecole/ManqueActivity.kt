package com.example.autoecole

import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.autoecole.R
import com.example.autoecole.global.DB

class ManqueActivity : AppCompatActivity() {

    private lateinit var database: DB
    private var prixPermis: Double = 0.0
    private lateinit var listView: ListView
    private lateinit var studentListAdapter: StudentListAdapter

    // Modèle de données représentant un étudiant
    data class Student(val id: Int, val fname: String, val sname: String, val manque: Double)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manque)

        // Initialisation de la base de données
        database = DB(this)
        listView = findViewById(R.id.listView)

        // Récupération du bouton de mise à jour
        val updateButton = findViewById<View>(R.id.updateButton)
        updateButton.setOnClickListener {
            updateManqueValue() // Appel à la fonction pour mettre à jour les valeurs de manque
            showManqueValue() // Appel à la fonction pour afficher les valeurs de manque
        }
    }

    // Fonction pour mettre à jour les valeurs de manque des étudiants
    private fun updateManqueValue() {
        val db = database.writableDatabase

        // Requête pour récupérer l'ID et le montant payé de chaque étudiant
        val selectQuery = "SELECT ID, FREE_PAID FROM STUDENT"

        // Requête pour récupérer le prix du permis
        val selectQueryPrix = "SELECT PRIX_PERMIS FROM PAIEMENT"

        // Exécution des requêtes
        val cursor = db.rawQuery(selectQuery, null)
        val cursorPrix = db.rawQuery(selectQueryPrix, null)

        var isSuccess = false

        while (cursor.moveToNext()) {
            val studentId = cursor.getInt(cursor.getColumnIndex("ID"))

            // Vérification si le curseur de la requête du prix du permis a une entrée valide
            if (cursorPrix.moveToFirst()) {
                val prixPermis = cursorPrix.getDouble(cursorPrix.getColumnIndex("PRIX_PERMIS"))
                val freePaid = cursor.getDouble(cursor.getColumnIndex("FREE_PAID"))
                val manque = prixPermis - freePaid

                // Mise à jour de la valeur de manque pour l'étudiant courant
                val updateQuery = "UPDATE STUDENT SET MANQUE = $manque WHERE ID = $studentId"
                db.execSQL(updateQuery)
                isSuccess = true
            }
        }

        cursor.close()
        cursorPrix.close()

        // Affichage d'un message toast indiquant le succès ou l'échec de la mise à jour
        if (isSuccess) {
            showToastManque("تم التحديث بنجاح")
        } else {
            showToastManque("فشل أثناء التحديث")
        }
    }

    // Fonction pour afficher les valeurs de manque des étudiants
    private fun showManqueValue() {
        val db = database.writableDatabase

        // Requête pour récupérer l'ID, le prénom, le nom et le manque de chaque étudiant ayant un manque positif
        val selectQuery = "SELECT ID, FNAME, SNAME, MANQUE FROM STUDENT WHERE MANQUE > 0"
        val cursor = db.rawQuery(selectQuery, null)

        val studentList = ArrayList<Student>()

        while (cursor.moveToNext()) {
            val studentId = cursor.getInt(cursor.getColumnIndex("ID"))
            val fname = cursor.getString(cursor.getColumnIndex("FNAME"))
            val sname = cursor.getString(cursor.getColumnIndex("SNAME"))
            val manque = cursor.getDouble(cursor.getColumnIndex("MANQUE"))

            // Création d'un objet Student pour chaque étudiant et ajout à la liste
            val student = Student(studentId, fname, sname, manque)
            studentList.add(student)
        }

        cursor.close()

        // Initialisation de l'adaptateur pour la liste des étudiants
        studentListAdapter = StudentListAdapter(this, studentList)
        listView.adapter = studentListAdapter

        // Affichage d'un message toast indiquant s'il y a des étudiants avec un manque ou non
        if (studentList.isNotEmpty()) {
            showToast("قائمة الطلاب الذين لديهم نقص في الدفع ")
        } else {
            showToast("لا يوجد نقص في الدفع")
        }
    }

    // Fonction pour afficher un message toast pour les opérations réussies de mise à jour de manque
    private fun showToastManque(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Fonction pour afficher un message toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
