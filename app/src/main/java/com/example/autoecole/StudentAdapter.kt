package com.example.autoecole

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.autoecole.R

class StudentAdapter(private val context: Context) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var cursor: Cursor? = null




    fun setStudents(cursor: Cursor?) {
        this.cursor = cursor
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        val modifyBtn = view.findViewById<Button>(R.id.modifyBtn)

        return StudentViewHolder(view)


    }


    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        cursor?.apply {
            moveToPosition(position)
            val Id = getString(getColumnIndexOrThrow("ID"))
            val fName = getString(getColumnIndexOrThrow("FNAME"))
            val sName = getString(getColumnIndexOrThrow("SNAME"))
            val birthdate = getString(getColumnIndexOrThrow("BIRTHDATE"))
            val nation = getString(getColumnIndexOrThrow("NATION"))
            val address = getString(getColumnIndexOrThrow("ADRESSE"))
            val phoneNumber = getInt(getColumnIndexOrThrow("PHONENUMBER"))
            val email = getString(getColumnIndexOrThrow("EMAIL"))
            val freePaid = getDouble(getColumnIndexOrThrow("FREE_PAID"))
            holder.txtIdValue.text = Id
            holder.txtFirstNameValue.text = fName
            holder.txtLastNameValue.text = sName
            holder.txtBirthdateValue.text = birthdate
            holder.txtNationValue.text = nation
            holder.txtAddressValue.text = address
            holder.txtPhoneNumberValue.text = phoneNumber.toString()
            holder.txtEmailValue.text = email
            holder.txtFreePaidValue.text = freePaid.toString()

            // Set click listener for modifyBtn
            holder.modifyBtn.setOnClickListener {
                val intent = Intent(context, ModifyStudentActivity::class.java)
                intent.putExtra("student_id", Id) // Pass the student ID as an extra
                context.startActivity(intent)
            }
        }

    }


    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtIdValue: TextView = itemView.findViewById(R.id.txtIdValue)
        val txtFirstNameValue: TextView = itemView.findViewById(R.id.txtFirstNameValue)
        val txtLastNameValue: TextView = itemView.findViewById(R.id.txtLastNameValue)
        val txtBirthdateValue: TextView = itemView.findViewById(R.id.txtBirthdateValue)
        val txtNationValue: TextView = itemView.findViewById(R.id.txtNationValue)
        val txtAddressValue: TextView = itemView.findViewById(R.id.txtAddressValue)
        val txtPhoneNumberValue: TextView = itemView.findViewById(R.id.txtPhoneNumberValue)
        val txtEmailValue: TextView = itemView.findViewById(R.id.txtEmailValue)
        val txtFreePaidValue: TextView = itemView.findViewById(R.id.txtFreePaidValue)
        val modifyBtn: Button = itemView.findViewById(R.id.modifyBtn) // Add this line

        init {
            modifyBtn.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    cursor?.apply {
                        moveToPosition(position)
                        val Id = getString(getColumnIndexOrThrow("ID"))
                        val intent = Intent(context, ModifyStudentActivity::class.java)
                        intent.putExtra("student_id", Id) // Pass the student ID as an extra
                        context.startActivity(intent)
                    }
                }
            }
        }
    }

}
