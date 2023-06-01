package com.example.autoecole


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.autoecole.databinding.ActivityAddNewMemberBinding
import com.example.autoecole.global.DB
import com.example.autoecole.global.MyFunction

class AddNewMemberActivity : AppCompatActivity() {
    var db: DB? = null


    private lateinit var BtnConfirmAdd: Button
    lateinit var binding: ActivityAddNewMemberBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DB(this@AddNewMemberActivity)


        binding.confirmAddBtn.setOnClickListener() {
            if (validate()) {
                saveData()
            }
        }
    }

    private fun validate(): Boolean {
        if (binding.edtName.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل الاسم", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.edtSecondName.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل اللقب", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.birthDate.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل تاريخ الميلاد", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.nation.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل الجنسية", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.adresse.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل العنوان", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.phoneNumber.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل رقم الهاتف", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.email.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل البريد الالكتروني", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.feePaid.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "أدخل المبلغ", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun saveData() {

        try {
            val firstName = binding.edtName.text.toString().trim()
            val lastName = binding.edtSecondName.text.toString().trim()
            val birthDate = binding.birthDate.text.toString().trim()
            val nationality = binding.nation.text.toString().trim()
            val address = binding.adresse.text.toString().trim()
            val phoneNumber = binding.phoneNumber.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val feePaid = binding.feePaid.text.toString().trim()


            val sqlQuery =
                "INSERT OR REPLACE INTO STUDENT(ID,FNAME,SNAME,BIRTHDATE,NATION,ADRESSE,PHONENUMBER,EMAIL,FREE_PAID)VALUES" + "('" + getIncrementedId() + "','" + firstName + "','" + lastName + "','" + birthDate + "','" + nationality + "','" + address + "','" + phoneNumber + "','" + email + "','" + feePaid + "')"

            db?.executeQuery(sqlQuery)

            Toast.makeText(this, "تم الحفظ بنجاح...", Toast.LENGTH_SHORT).show()
            clearFields()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    private fun getIncrementedId(): String {
        var incrementedId = ""
        try {
            val sqlQuery = "SELECT IFNULL(MAX(ID)+1,'1') AS ID FROM STUDENT"
            db?.fireQuery(sqlQuery)?.use {
                if (it.count > 0) {
                    incrementedId = MyFunction.getValue(it, "ID")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return incrementedId
    }
    private fun clearFields() {
        binding.edtName.text.clear()
        binding.edtSecondName.text.clear()
        binding.birthDate.text.clear()
        binding.nation.text.clear()
        binding.adresse.text.clear()
         binding.phoneNumber.text.clear()
        binding.email.text.clear()
         binding.feePaid.text.clear()
    }

}
