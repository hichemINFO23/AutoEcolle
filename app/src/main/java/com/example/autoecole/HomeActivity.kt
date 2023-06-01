package com.example.autoecole

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.autoecole.databinding.ActivityHomeBinding
import com.example.autoecole.global.DB
import com.example.autoecole.manager.SessionManager
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var managementBtn: Button
    private lateinit var planningBtn: Button
    private lateinit var paiementBtn: Button
    private lateinit var changepassBtn: Button
    private lateinit var logoutBtn: Button
    var session: SessionManager? = null
    var db: DB? = null

    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DB(this)
        session = SessionManager(this)
        managementBtn = findViewById(R.id.managementBtn)
        planningBtn = findViewById(R.id.planningBtn)
        paiementBtn = findViewById(R.id.paiementBtn)
        changepassBtn = findViewById(R.id.changePasswordBtn)
        logoutBtn = findViewById(R.id.logoutBtn)


        managementBtn.setOnClickListener() {
            val intent = Intent(this, ManagementPersonActivity::class.java)
            startActivity(intent)
        }
        planningBtn.setOnClickListener() {
            val intent = Intent(this, MainPlanningActivity::class.java)
            startActivity(intent)
        }
        paiementBtn.setOnClickListener() {
            val intent = Intent(this, MainPaiementActivity::class.java)
            startActivity(intent)
        }
        changepassBtn.setOnClickListener() {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }
        logoutBtn.setOnClickListener() {
            logout()
        }
    }


    fun logout() {
        session?.setLogin(false)
        val intent = Intent(this, LoginPageActivity::class.java)
        startActivity(intent)
    }
}