package com.example.autoecole

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.autoecole.global.DB

class AjoutPaiementActivity : AppCompatActivity() {

    private lateinit var db: DB
    private lateinit var students: List<Student>
    private var selectedStudentId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajout_paiement)

        db = DB(this)

        // Remplir le spinner avec les noms des étudiants
        students = getStudents()
        val studentNames = students.map { "${it.firstName} ${it.lastName}" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, studentNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinnerStudents = findViewById<Spinner>(R.id.spinnerStudents)
        spinnerStudents.adapter = adapter

        spinnerStudents.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedStudentId = students[position].id
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedStudentId = -1
            }
        }

        val btnAddPayment = findViewById<Button>(R.id.btnAddPayment)
        val etPaymentAmount = findViewById<EditText>(R.id.etPaymentAmount)

        btnAddPayment.setOnClickListener {
            val amount = etPaymentAmount.text.toString().toDoubleOrNull()
            if (amount != null && selectedStudentId != -1) {
                updateStudentPayment(amount)
                Toast.makeText(this, "تم اضافة الدفع بنجاح", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "فشل أثناء اضافة الدفع", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Méthode pour obtenir la liste des étudiants depuis la base de données
    private fun getStudents(): List<Student> {
        // Récupérer les étudiants depuis la base de données
        val cursor = db.fireQuery("SELECT * FROM STUDENT")
        val students = mutableListOf<Student>()
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("ID"))
                val firstName = cursor.getString(cursor.getColumnIndex("FNAME"))
                val lastName = cursor.getString(cursor.getColumnIndex("SNAME"))
                val birthDate = cursor.getString(cursor.getColumnIndex("BIRTHDATE"))
                val nation = cursor.getString(cursor.getColumnIndex("NATION"))
                val address = cursor.getString(cursor.getColumnIndex("ADRESSE"))
                val phoneNumber = cursor.getInt(cursor.getColumnIndex("PHONENUMBER"))
                val email = cursor.getString(cursor.getColumnIndex("EMAIL"))
                val feePaid = cursor.getDouble(cursor.getColumnIndex("FREE_PAID"))
                val manque = cursor.getDouble(cursor.getColumnIndex("MANQUE"))

                students.add(Student(id, firstName, lastName, birthDate, nation, address, phoneNumber, email, feePaid, manque))
            } while (cursor.moveToNext())
        }
        cursor?.close()

        return students
    }

    // Méthode pour mettre à jour le paiement de l'étudiant
    private fun updateStudentPayment(amount: Double) {
        val student = students.find { it.id == selectedStudentId }
        if (student != null) {
            val newFeePaid = student.feePaid + amount
            val updateQuery = "UPDATE STUDENT SET FREE_PAID = $newFeePaid WHERE ID = $selectedStudentId"
            db.executeQuery(updateQuery)
        }
    }
}

// Classe représentant un étudiant
data class Student(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val nation: String,
    val address: String,
    val phoneNumber: Int,
    val email: String,
    val feePaid: Double,
    val manque: Double
)
