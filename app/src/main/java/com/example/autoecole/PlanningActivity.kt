package com.example.autoecole

import android.database.Cursor
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.autoecole.global.DB

class PlanningActivity : AppCompatActivity() {
    private lateinit var sessionListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planning)

        sessionListView = findViewById(R.id.sessionListView)

        val db = DB(this)
        val sessionCursor: Cursor? = db.fireQuery("SELECT * FROM SESSION ORDER BY DATE, TIME")
        sessionCursor?.let { cursor ->
            val sessionList: MutableList<String> = mutableListOf()
            if (cursor.moveToFirst()) {
                do {
                    val sessionDetails = "رقم الحصة: ${cursor.getInt(cursor.getColumnIndex("ID"))}\n" +
                            "رقم الطالب : ${cursor.getInt(cursor.getColumnIndex("STUDENT_ID"))}\n" +
                            "التاريخ: ${cursor.getString(cursor.getColumnIndex("DATE"))}\n" +
                            "الساعة: ${cursor.getString(cursor.getColumnIndex("TIME"))}\n" +
                            "رقم المدرب: ${cursor.getInt(cursor.getColumnIndex("MONITEUR_ID"))}\n" +
                            "المكان: ${cursor.getString(cursor.getColumnIndex("PLACE"))}"
                    sessionList.add(sessionDetails)
                } while (cursor.moveToNext())
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, sessionList)
            sessionListView.adapter = adapter
        }

        sessionCursor?.close()
        db.close()
    }
}
