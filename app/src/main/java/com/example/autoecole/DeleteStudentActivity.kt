package com.example.autoecole

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.autoecole.R
import com.example.autoecole.global.DB

class DeleteStudentActivity : AppCompatActivity() {

    private lateinit var etStudentId: EditText
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_student)

        etStudentId = findViewById(R.id.etStudentName)
        btnDelete = findViewById(R.id.btnDelete)

        btnDelete.setOnClickListener {
            val studentId = etStudentId.text.toString().trim().toIntOrNull()

            if (studentId != null) {
                deleteStudent(studentId)
            } else {
                Toast.makeText(this, "أدخل رقم طالب صحيح", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun deleteStudent(studentId: Int) {
        val db = DB(this) // Create an instance of your database helper class

        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("تأكيد الحذف")
        alertDialogBuilder.setMessage("هل أنت متأكد من حذف الطالب بالرقم: $studentId؟")
        alertDialogBuilder.setPositiveButton("تأكيد") { _, _ ->
            val sql = "DELETE FROM STUDENT WHERE ID = $studentId"
            val success = db.executeQuery(sql)

            if (success) {
                Toast.makeText(this, "تم الحذف بنجاح...", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "فشل أثناء الحذفظ", Toast.LENGTH_SHORT).show()
            }
        }
        alertDialogBuilder.setNegativeButton("الغاء") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}


