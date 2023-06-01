package com.example.autoecole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.autoecole.databinding.ActivityMainBinding
import com.example.autoecole.global.DB
import com.example.autoecole.manager.SessionManager


class SplashScreenActivity : AppCompatActivity() {
    private var mDelayHandler: Handler? = null
    private val splashDelay: Long = 3000
    var db: DB? = null
    var session: SessionManager? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DB(this)
        session = SessionManager(this)
        insertAdminData()

        // Set the login status in SessionManager based on the login state
        session?.setLogin(session?.isLoggedIn == true)

        mDelayHandler = Handler()
        mDelayHandler?.postDelayed(mRunnable, splashDelay)
    }

    private val mRunnable: Runnable = Runnable {
        val intent = Intent(this, LaunchActivity::class.java)
        startActivity(intent)
        finish()

    }

    private fun insertAdminData() {
        try {
            val sqlCheck = "SELECT * FROM ADMIN"
            db?.fireQuery(sqlCheck)?.use {
                if (it.count > 0) {
                    Log.d("SplashActivity", "data available")
                } else {
                    val sqlQuery =
                        "INSERT OR REPLACE INTO ADMIN(ID,USER_NAME,PASSWORD,MOBILE)VALUES('1','admin','1234','0664898670')"
                    db?.executeQuery(sqlQuery)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDelayHandler?.removeCallbacks(mRunnable)
    }
}
