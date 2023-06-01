package com.example.autoecole

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.autoecole.databinding.ActivityLoginPageBinding
import com.example.autoecole.global.DB
import com.example.autoecole.manager.SessionManager

class LoginPageActivity : AppCompatActivity() {

    var db: DB? = null
    var session: SessionManager? = null
    var UserName: EditText? = null
    var Password: EditText? = null
    lateinit var binding: ActivityLoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflation de la mise en page en utilisant le view binding.
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialisation de la base de données et du gestionnaire de session.
        db = DB(this)
        session = SessionManager(this)

        // Récupération des références des champs d'édition du nom d'utilisateur et du mot de passe.
        UserName = binding.username
        Password = binding.password

        // Définition d'un écouteur de clic sur le bouton de connexion.
        binding.loginButton.setOnClickListener {
            getLogin()
        }
    }

    // Fonction pour gérer le processus de connexion.
    fun getLogin() {
        try {
            // Construction de la requête SQL pour vérifier si les identifiants saisis correspondent à l'utilisateur administrateur.
            val sqlQuery = "SELECT * FROM ADMIN WHERE USER_NAME='" + UserName?.text.toString().trim() + "' " +
                    "AND PASSWORD= '" + Password?.text.toString().trim() + "' AND ID ='1'"

            // Exécution de la requête et traitement du résultat.
            db?.fireQuery(sqlQuery)?.use { cursor ->
                if (cursor.count > 0) {
                    // Connexion réussie.
                    session?.setLogin(true)
                    Toast.makeText(this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show()

                    // Démarrage de l'activité principale (HomeActivity).
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Échec de la connexion.
                    session?.setLogin(false)
                    Toast.makeText(this, "فشل اثناء تسجيل الدخول", Toast.LENGTH_SHORT).show()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
