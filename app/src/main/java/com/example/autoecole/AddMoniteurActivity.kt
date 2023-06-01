package com.example.autoecole

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.autoecole.databinding.ActivityAddMoniteurBinding
import com.example.autoecole.global.DB
import com.example.autoecole.global.MyFunction

class AddMoniteurActivity : AppCompatActivity() {
    var db: DB? = null

    private lateinit var BtnConfirmAdd: Button
    lateinit var binding: ActivityAddMoniteurBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMoniteurBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DB(this)

        // Configuration du bouton pour enregistrer les données
        binding.confirmAddBtn.setOnClickListener() {
            if (validate()) {
                saveData()
            }
        }
    }

    // Méthode de validation des champs
    private fun validate(): Boolean {
        if (binding.edtName.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل الاسم", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.edtSecondName.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل اللقب", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.birthDate.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل تاريخ الميلاد", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.nation.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل الجنسية", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.adresse.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل العنوان", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.phoneNumber.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل رقم الهاتف", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.email.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل البريد الالكتروني", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.matricule.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل رقم تسجيل السيارة", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.type.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل نوع السيارة", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.marque.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل شعار السيارة", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    // Méthode pour enregistrer les données dans la base de données
    private fun saveData() {
        try {
            val firstName = binding.edtName.text.toString().trim()
            val lastName = binding.edtSecondName.text.toString().trim()
            val birthDate = binding.birthDate.text.toString().trim()
            val nationality = binding.nation.text.toString().trim()
            val address = binding.adresse.text.toString().trim()
            val phoneNumber = binding.phoneNumber.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val matricule = binding.matricule.text.toString().trim()
            val marque = binding.marque.text.toString().trim()
            val type = binding.type.text.toString().trim()

            // Création de la requête SQL pour insérer ou mettre à jour les données du moniteur
            val sqlQuery =
                "INSERT OR REPLACE INTO MONITEUR(ID , FNAME , SNAME , BIRTHDATE , NATION , ADRESSE , PHONENUMBER , EMAIL ,MATRICULE ,MARQUE,TYPE)VALUES" + "('" + getIncrementedId() + "','" + firstName + "','" + lastName + "','" + birthDate + "','" + nationality + "','" + address + "','" + phoneNumber + "','" + email + "','" + matricule + "','" + marque + "','" + type + "')"

            // Exécution de la requête SQL
            db?.executeQuery(sqlQuery)

            Toast.makeText(this, "تم الحفظ بنجاح...", Toast.LENGTH_SHORT).show()
            clearFields() // Efface les champs après l'enregistrement réussi
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    // Méthode pour obtenir l'ID incrémenté pour le nouveau moniteur
    private fun getIncrementedId(): String {
        var incrementedId = ""
        try {
            val sqlQuery = "SELECT IFNULL(MAX(ID)+1,'1') AS ID FROM MONITEUR"
            db?.fireQuery(sqlQuery)?.use {
                if (it.count > 0) {
                    incrementedId = MyFunction.getValue(it, "ID")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return incrementedId
    }

    // Méthode pour effacer les champs après l'enregistrement réussi
    private fun clearFields() {
        binding.edtName.text.clear()
        binding.edtSecondName.text.clear()
        binding.birthDate.text.clear()
        binding.nation.text.clear()
        binding.adresse.text.clear()
        binding.phoneNumber.text.clear()
        binding.email.text.clear()
        binding.matricule.text.clear()
        binding.marque.text.clear()
        binding.type.text.clear()
    }

}
