package com.example.autoecole

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.autoecole.global.DB

class ModifyStudentActivity : AppCompatActivity() {

    private lateinit var db: DB
    private lateinit var studentIdEditText: EditText
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var birthDateEditText: EditText
    private lateinit var nationEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var paidEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_student)

        // Initialisation de l'instance de la base de données
        db = DB(this)

        // Initialisation des vues
        studentIdEditText = findViewById(R.id.editTextStudentId)
        firstNameEditText = findViewById(R.id.editTextFirstName)
        lastNameEditText = findViewById(R.id.editTextLastName)
        birthDateEditText = findViewById(R.id.editTextBirthDate)
        nationEditText = findViewById(R.id.editTextNation)
        addressEditText = findViewById(R.id.editTextAddress)
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber)
        emailEditText = findViewById(R.id.editTextEmail)
        paidEditText = findViewById(R.id.editTextPaid)
        saveButton = findViewById(R.id.buttonSave)

        // Ajout d'un écouteur de clic sur le bouton "Enregistrer"
        saveButton.setOnClickListener {
            // Récupération des valeurs entrées dans les champs
            val studentId = studentIdEditText.text.toString().toIntOrNull()
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val birthDate = birthDateEditText.text.toString()
            val nation = nationEditText.text.toString()
            val address = addressEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString().toLongOrNull()
            val email = emailEditText.text.toString()
            val paid = paidEditText.text.toString().toDoubleOrNull()

            // Vérification que toutes les valeurs sont valides et non nulles
            if (studentId != null && !firstName.isNullOrEmpty() && !lastName.isNullOrEmpty()
                && !birthDate.isNullOrEmpty() && !nation.isNullOrEmpty() && !address.isNullOrEmpty()
                && phoneNumber != null && !email.isNullOrEmpty() && paid != null) {

                // Construction de la requête SQL pour mettre à jour les informations de l'étudiant
                val updateQuery =
                    "UPDATE STUDENT SET FNAME = '$firstName', SNAME = '$lastName', BIRTHDATE = '$birthDate'," +
                            " NATION = '$nation', ADRESSE = '$address', PHONENUMBER = $phoneNumber, " +
                            "EMAIL = '$email', FREE_PAID = $paid WHERE ID = $studentId"

                // Exécution de la requête de mise à jour et récupération du résultat
                val success = db.executeQuery(updateQuery)

                // Affichage d'un message toast indiquant le succès ou l'échec de la mise à jour
                if (success) {
                    Toast.makeText(
                        this,
                        "تم التحديث بنجاح",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Réinitialisation des champs
                    clearFields()
                } else {
                    Toast.makeText(
                        this,
                        "فشل أثناء التحديث",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "أدخل المعلومات بطريقة صحيحة",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Fonction pour réinitialiser les champs
    private fun clearFields() {
        studentIdEditText.text.clear()
        firstNameEditText.text.clear()
        lastNameEditText.text.clear()
        birthDateEditText.text.clear()
        nationEditText.text.clear()
        addressEditText.text.clear()
        phoneNumberEditText.text.clear()
        emailEditText.text.clear()
        paidEditText.text.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Fermeture de la connexion à la base de données
        db.close()
    }
}
