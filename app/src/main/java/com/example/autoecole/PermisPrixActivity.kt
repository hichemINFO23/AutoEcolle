package com.example.autoecole

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.autoecole.global.DB

class PermisPrixActivity : AppCompatActivity() {
    private lateinit var editPermisPrix: EditText
    private lateinit var btnUpdatePermisPrix: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permis_prix)

        editPermisPrix = findViewById(R.id.editPermisPrix)
        btnUpdatePermisPrix = findViewById(R.id.btnUpdatePermisPrix)

        btnUpdatePermisPrix.setOnClickListener {
            val permisPrix = editPermisPrix.text.toString()
            insertPermisPrix(permisPrix)
        }
    }

    private fun insertPermisPrix(permisPrix: String) {
        val sql = "INSERT INTO PAIEMENT (PRIX_PERMIS) VALUES ('$permisPrix')"
        val db = DB(this)
        val success = db.executeQuery(sql)
        db.close()

        if (success) {
            Toast.makeText(this, "تم التحديث بنجاح", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "فشل أثناء التحديث", Toast.LENGTH_SHORT).show()
        }
    }
}
