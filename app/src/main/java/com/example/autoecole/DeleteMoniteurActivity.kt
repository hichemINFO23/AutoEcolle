package com.example.autoecole

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.autoecole.R
import com.example.autoecole.global.DB

class DeleteMoniteurActivity : AppCompatActivity() {

    private lateinit var etStudentName: EditText
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_moniteur)

        etStudentName = findViewById(R.id.etStudentName) // Référence au champ de texte pour saisir le nom de l'étudiant
        btnDelete = findViewById(R.id.btnDelete) // Référence au bouton de suppression

        btnDelete.setOnClickListener {
            val studentName = etStudentName.text.toString().trim() // Récupère le nom de l'étudiant saisi dans le champ de texte

            if (studentName.isNotEmpty()) {
                deleteStudent(studentName) // Appel de la fonction pour supprimer l'étudiant
            } else {
                Toast.makeText(this, "من فضلك أدخل اسم المدرب", Toast.LENGTH_SHORT).show() // Affiche un message si aucun nom d'étudiant n'est saisi
            }
        }
    }

    private fun deleteStudent(studentName: String) {
        val db = DB(this) // Crée une instance de la classe d'aide à la base de données

        val alertDialogBuilder = AlertDialog.Builder(this) // Crée un constructeur d'AlertDialog
        alertDialogBuilder.setTitle("تأكيد الحذف") // Définit le titre de la boîte de dialogue
        alertDialogBuilder.setMessage("هل أنت متأكد من حذف : $studentName؟") // Définit le message de la boîte de dialogue en utilisant le nom de l'étudiant
        alertDialogBuilder.setPositiveButton("تأكيد") { _, _ ->
            // Lorsque le bouton de confirmation est cliqué

            val sql = "DELETE FROM MONITEUR WHERE FNAME = '$studentName'" // Requête SQL pour supprimer l'étudiant avec le nom spécifié
            val success = db.executeQuery(sql) // Exécute la requête de suppression dans la base de données

            if (success) {
                Toast.makeText(this, "تم الحذف بنجاح...", Toast.LENGTH_SHORT).show() // Affiche un message de succès si la suppression est réussie
            } else {
                Toast.makeText(this, "فشل أثناء الحذفظ", Toast.LENGTH_SHORT).show() // Affiche un message d'échec si la suppression échoue
            }
        }
        alertDialogBuilder.setNegativeButton("الغاء") { dialog, _ ->
            // Lorsque le bouton d'annulation est cliqué, ferme la boîte de dialogue
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create() // Crée la boîte de dialogue à partir du constructeur
        alertDialog.show() // Affiche la boîte de dialogue à l'écran
    }
}
