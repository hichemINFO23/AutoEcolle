package com.example.autoecole

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.autoecole.global.DB

class SearchStudentActivity : AppCompatActivity() {
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var db: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_student)

        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)
        resultTextView = findViewById(R.id.resultTextView)
        db = DB(this)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val searchTerm = s.toString().trim()
                if (searchTerm.isNotEmpty()) {
                    searchButton.isEnabled = true
                } else {
                    searchButton.isEnabled = false
                }
            }
        })

        searchButton.setOnClickListener {
            val searchTerm = searchEditText.text.toString().trim()
            searchStudent(searchTerm)
        }
    }

    private fun searchStudent(searchTerm: String) {
        val query =
            "SELECT * FROM STUDENT WHERE FNAME LIKE '%$searchTerm%' OR SNAME LIKE '%$searchTerm%'"
        val cursor = db.fireQuery(query)

        val resultBuilder = StringBuilder()
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("ID"))
                val firstName = cursor.getString(cursor.getColumnIndex("FNAME"))
                val lastName = cursor.getString(cursor.getColumnIndex("SNAME"))
                val birthDate = cursor.getString(cursor.getColumnIndex("BIRTHDATE"))
                val nation = cursor.getString(cursor.getColumnIndex("NATION"))
                val address = cursor.getString(cursor.getColumnIndex("ADRESSE"))
                val phoneNumber = cursor.getString(cursor.getColumnIndex("PHONENUMBER"))
                val email = cursor.getString(cursor.getColumnIndex("EMAIL"))
                val freePaid = cursor.getDouble(cursor.getColumnIndex("FREE_PAID"))

                resultBuilder.append(" الرقم  : $id\n")
                resultBuilder.append(" الاسم : $firstName\n")
                resultBuilder.append("اللقب : $lastName\n")
                resultBuilder.append("تاريخ الميلاد : $birthDate\n")
                resultBuilder.append("الجنسية : $nation\n")
                resultBuilder.append("العنوان : $address\n")
                resultBuilder.append(" رقم الهاتف : $phoneNumber\n")
                resultBuilder.append("  البريد الالكتروني : $email\n")
                resultBuilder.append("   المبلغ المدفوع :$freePaid\n")
                resultBuilder.append("__________________________________________________________\n")
            } while (cursor.moveToNext())

            cursor.close()
        } else {
            resultBuilder.append("لا توجد نتيجة...")
        }

        resultTextView.text = resultBuilder.toString()
    }
}
