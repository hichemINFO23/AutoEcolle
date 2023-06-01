package com.example.autoecole

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autoecole.global.DB

class MoniteurListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var db: DB
    private lateinit var adapter: MoniteurAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moniteur_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MoniteurAdapter(this)


        db = DB(this)
        val cursor = db.fireQuery("SELECT * FROM MONITEUR")
        adapter.setMoniteur(cursor)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}
