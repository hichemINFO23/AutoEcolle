package com.example.autoecole

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.autoecole.global.DB

class ModifyMoniteurActivity : AppCompatActivity() {
    private lateinit var db: DB
    private lateinit var studentIdEditText: EditText
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var birthDateEditText: EditText
    private lateinit var nationEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var matriculeEditText: EditText
    private lateinit var marqueEditText: EditText
    private lateinit var typeEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_moniteur)

        // Initialize DB instance
        db = DB(this)

        // Initialize views
        studentIdEditText = findViewById(R.id.editTextStudentId)
        firstNameEditText = findViewById(R.id.editTextFirstName)
        lastNameEditText = findViewById(R.id.editTextLastName)
        birthDateEditText = findViewById(R.id.editTextBirthDate)
        nationEditText = findViewById(R.id.editTextNation)
        addressEditText = findViewById(R.id.editTextAddress)
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber)
        emailEditText = findViewById(R.id.editTextEmail)
        matriculeEditText = findViewById(R.id.editTextMatricule)
        marqueEditText = findViewById(R.id.editTextMarque)
        typeEditText = findViewById(R.id.editTextType)


        saveButton = findViewById(R.id.buttonSave)

        saveButton.setOnClickListener {
            val studentId = studentIdEditText.text.toString().toIntOrNull()
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val birthDate = birthDateEditText.text.toString()
            val nation = nationEditText.text.toString()
            val address = addressEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString().toLongOrNull()
            val email = emailEditText.text.toString()
            val matricule_ = matriculeEditText.text.toString()
            val marque_ = marqueEditText.text.toString()
            val type_ = typeEditText.text.toString()

            if (studentId != null && !firstName.isNullOrEmpty() && !lastName.isNullOrEmpty()
                && !birthDate.isNullOrEmpty() && !nation.isNullOrEmpty() && !address.isNullOrEmpty()
                && phoneNumber != null && !email.isNullOrEmpty() && matricule_ != null && marque_ != null&& type_ != null) {

                val updateQuery =
                    "UPDATE MONITEUR SET FNAME = '$firstName', SNAME = '$lastName', BIRTHDATE = '$birthDate'," +
                            " NATION = '$nation', ADRESSE = '$address', PHONENUMBER = $phoneNumber, " +
                            "EMAIL = '$email', MATRICULE = '$matricule_' ,MARQUE= '$marque_', Type= '$type_' WHERE ID = $studentId"

                val success = db.executeQuery(updateQuery)

                if (success) {
                    Toast.makeText(
                        this,
                        "تم التحديث بنجاح",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Reset fields
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

    private fun clearFields() {
        studentIdEditText.text.clear()
        firstNameEditText.text.clear()
        lastNameEditText.text.clear()
        birthDateEditText.text.clear()
        nationEditText.text.clear()
        addressEditText.text.clear()
        phoneNumberEditText.text.clear()
        emailEditText.text.clear()
        matriculeEditText.text.clear()
        marqueEditText.text.clear()
        typeEditText.text.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Close the database connection
        db.close()
    }
}
