package com.example.autoecole

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.autoecole.global.DB

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var db: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        db = DB(this) // Initialisation de la base de données

        val btnChangePassword: Button = findViewById(R.id.btnChangePassword)
        btnChangePassword.setOnClickListener {
            // Récupération des valeurs saisies par l'utilisateur
            val oldPassword: String = findViewById<EditText>(R.id.etOldPassword).text.toString()
            val newPassword: String = findViewById<EditText>(R.id.etNewPassword).text.toString()
            val confirmPassword: String = findViewById<EditText>(R.id.etConfirmPassword).text.toString()

            if (newPassword == confirmPassword) {
                // Si le nouveau mot de passe correspond à la confirmation

                val success = db.updateAdminPassword(oldPassword, newPassword) // Mise à jour du mot de passe de l'administrateur dans la base de données

                if (success) {
                    // Si la mise à jour du mot de passe est réussie
                    Toast.makeText(this, "تم تغيير كلمة المرور بنجاح", Toast.LENGTH_SHORT).show()
                    finish() // Fermer l'activité après le changement de mot de passe réussi
                } else {
                    // Si la mise à jour du mot de passe échoue
                    Toast.makeText(this, "فشل أثناء تغيير كلمة المرور", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Si le nouveau mot de passe ne correspond pas à la confirmation
                Toast.makeText(this, "كلمة المرور الجديدة غير متوافقة", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
