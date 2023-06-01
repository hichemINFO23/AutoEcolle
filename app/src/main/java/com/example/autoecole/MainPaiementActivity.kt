package com.example.autoecole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainPaiementActivity : AppCompatActivity() {

    private lateinit var manqueBtn:Button
    private lateinit var ajoutBtn:Button
    private lateinit var fixBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_paiement)
        manqueBtn=findViewById(R.id.manqueBtn)
        ajoutBtn=findViewById(R.id.ajoutBtn)
        fixBtn=findViewById(R.id.fixBtn)

        manqueBtn.setOnClickListener(){
            val intent = Intent(this, ManqueActivity::class.java)
            startActivity(intent)
        }
        ajoutBtn.setOnClickListener(){
            val intent = Intent(this, AjoutPaiementActivity::class.java)
            startActivity(intent)
        }
            fixBtn.setOnClickListener(){
                val intent = Intent(this, PermisPrixActivity::class.java)
                startActivity(intent)
            }
    }
}