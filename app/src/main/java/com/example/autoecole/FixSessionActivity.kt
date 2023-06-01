package com.example.autoecole

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.autoecole.global.DB

class FixSessionActivity : AppCompatActivity() {

    private lateinit var studentSpinner: Spinner
    private lateinit var dateEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var moniteurSpinner: Spinner
    private lateinit var placeEditText: EditText
    private lateinit var fixSessionButton: Button
    private lateinit var sendEmailButton: Button

    private lateinit var db: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fix_session)

        db = DB(this)

        studentSpinner = findViewById(R.id.studentSpinner) // Référence au Spinner pour les étudiants dans la mise en page
        dateEditText = findViewById(R.id.dateEditText) // Référence au champ de texte pour la date dans la mise en page
        timeEditText = findViewById(R.id.timeEditText) // Référence au champ de texte pour l'heure dans la mise en page
        moniteurSpinner = findViewById(R.id.moniteurSpinner) // Référence au Spinner pour les moniteurs dans la mise en page
        placeEditText = findViewById(R.id.placeEditText) // Référence au champ de texte pour le lieu dans la mise en page
        fixSessionButton = findViewById(R.id.fixSessionButton) // Référence au bouton de fixation de la session dans la mise en page
        sendEmailButton = findViewById(R.id.sendEmailButton) // Référence au bouton d'envoi de l'e-mail dans la mise en page

        loadMoniteurs() // Charge les moniteurs dans le Spinner
        loadStudents() // Charge les étudiants dans le Spinner

        fixSessionButton.setOnClickListener {
            val studentId = studentSpinner.selectedItemId.toString() // Récupère l'ID de l'étudiant sélectionné
            val date = dateEditText.text.toString() // Récupère la date saisie
            val time = timeEditText.text.toString() // Récupère l'heure saisie
            val moniteur = moniteurSpinner.selectedItem.toString() // Récupère le moniteur sélectionné
            val place = placeEditText.text.toString() // Récupère le lieu saisi

            if (date.isEmpty() || time.isEmpty() || place.isEmpty()) {
                Toast.makeText(this, "يرجى تعبئة جميع الحقول", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Construit la requête SQL pour insérer la session dans la base de données
            val query = "INSERT INTO SESSION (STUDENT_ID, DATE, TIME, MONITEUR_ID, PLACE) " +
                    "VALUES ($studentId, '$date', '$time', '$moniteur', '$place')"

            val success = db.executeQuery(query) // Exécute la requête d'insertion dans la base de données

            if (success) {
                Toast.makeText(this, "تم حجز الحصة بنجاح", Toast.LENGTH_SHORT).show()
                // La session a été fixée avec succès
                // Effectue les actions nécessaires ou affiche un message de réussite
            } else {
                Toast.makeText(this, "فشل في حجز الحصة", Toast.LENGTH_SHORT).show()
                // Échec de la fixation de la session
                // Gère l'échec, affiche un message d'erreur ou effectue les actions nécessaires
            }
        }

        sendEmailButton.setOnClickListener {
            val studentEmail = getEmailForSelectedStudent() // Récupère l'e-mail de l'étudiant sélectionné
            if (studentEmail.isNotEmpty()) {
                val sessionDetails = getSessionDetails() // Récupère les détails de la session
                sendEmail(studentEmail, sessionDetails) // Envoie l'e-mail avec les détails de la session
            } else {
                Toast.makeText(this, "لم يتم تحديد طالب", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Charge les étudiants dans le Spinner
    private fun loadStudents() {
        val studentCursor = db.readableDatabase.rawQuery("SELECT  FNAME, SNAME FROM STUDENT", null)
        val students = mutableListOf<String>()

        studentCursor.use {
            students.add("اختر الطالب") // Ajoute l'élément d'indice 0 comme indication dans la liste des étudiants
            while (it.moveToNext()) {
                val fname = it.getString(it.getColumnIndex("FNAME"))
                val sname = it.getString(it.getColumnIndex("SNAME"))
                val fullName = "$fname $sname"
                students.add(fullName) // Ajoute le nom complet de l'étudiant à la liste des étudiants
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, students)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        studentSpinner.adapter = adapter
        studentSpinner.setSelection(0) // Sélectionne l'élément d'indice 0 (l'indication) par défaut
    }

    // Charge les moniteurs dans le Spinner
    private fun loadMoniteurs() {
        val moniteurCursor = db.readableDatabase.rawQuery("SELECT FNAME, SNAME FROM MONITEUR", null)
        val moniteurs = mutableListOf<String>()

        moniteurs.add("اختر المدرب") // Ajoute l'élément d'indice 0 comme indication dans la liste des moniteurs

        moniteurCursor.use {
            while (it.moveToNext()) {
                val fname = it.getString(it.getColumnIndex("FNAME"))
                val sname = it.getString(it.getColumnIndex("SNAME"))
                val fullName = "$fname $sname"
                moniteurs.add(fullName) // Ajoute le nom complet du moniteur à la liste des moniteurs
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, moniteurs)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        moniteurSpinner.adapter = adapter

        moniteurSpinner.setSelection(0) // Sélectionne l'élément d'indice 0 (l'indication) par défaut
    }

    // Obtient l'e-mail de l'étudiant sélectionné
    private fun getEmailForSelectedStudent(): String {
        val selectedStudentPosition = studentSpinner.selectedItemPosition
        if (selectedStudentPosition > 0) {
            val selectedStudentCursor =
                db.fireQuery("SELECT EMAIL FROM STUDENT LIMIT 1 OFFSET ${selectedStudentPosition - 1}")
            selectedStudentCursor?.use {
                if (it.moveToFirst()) {
                    return it.getString(it.getColumnIndex("EMAIL")) // Récupère l'e-mail de l'étudiant
                }
            }
        }
        return ""
    }

    // Récupère les détails de la session
    private fun getSessionDetails(): String {
        val selectedStudent = studentSpinner.selectedItem.toString()
        val date = dateEditText.text.toString()
        val time = timeEditText.text.toString()
        val selectedMoniteur = moniteurSpinner.selectedItem.toString()
        val place = placeEditText.text.toString()

        val sessionDetails = StringBuilder()
        sessionDetails.append("تفاصيل الحصة : \n")
        sessionDetails.append("السيد : $selectedStudent \n")
        sessionDetails.append("التاريخ : $date \n")
        sessionDetails.append("الساعة : $time \n")
        sessionDetails.append("المدرب : $selectedMoniteur \n")
        sessionDetails.append("المكان : $place \n")

        return sessionDetails.toString()
    }

    // Envoie un e-mail avec les détails de la session
    private fun sendEmail(email: String, message: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        intent.putExtra(Intent.EXTRA_SUBJECT, "تفاصيل الحصة :")
        intent.putExtra(Intent.EXTRA_TEXT, message)
        startActivity(Intent.createChooser(intent, "أرسل البريد الالكتروني"))
    }
}
