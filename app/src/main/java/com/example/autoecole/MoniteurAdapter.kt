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

class MoniteurAdapter(private val context: Context) : RecyclerView.Adapter<MoniteurAdapter.MoniteurViewHolder>() {

    private var cursor: Cursor? = null

    // Fonction pour définir le curseur contenant les données des moniteurs
    fun setMoniteur(cursor: Cursor?) {
        this.cursor = cursor
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoniteurViewHolder {
        // Création de la vue de l'élément de liste en utilisant un fichier de mise en page (layout) XML
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_moniteur, parent, false)
        val modifyBtn = view.findViewById<Button>(R.id.modifyBtn)

        return MoniteurViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoniteurViewHolder, position: Int) {
        cursor?.apply {
            moveToPosition(position)
            // Récupération des données du moniteur à partir du curseur
            val Id = getString(getColumnIndexOrThrow("ID"))
            val fName = getString(getColumnIndexOrThrow("FNAME"))
            val sName = getString(getColumnIndexOrThrow("SNAME"))
            val birthdate = getString(getColumnIndexOrThrow("BIRTHDATE"))
            val nation = getString(getColumnIndexOrThrow("NATION"))
            val address = getString(getColumnIndexOrThrow("ADRESSE"))
            val phoneNumber = getInt(getColumnIndexOrThrow("PHONENUMBER"))
            val email = getString(getColumnIndexOrThrow("EMAIL"))
            val matricule = getInt(getColumnIndexOrThrow("MATRICULE"))
            val type = getString(getColumnIndexOrThrow("TYPE"))
            val marque = getString(getColumnIndexOrThrow("MARQUE"))

            // Mise à jour des valeurs des TextView dans le ViewHolder avec les données du moniteur
            holder.txtIdValue.text = Id
            holder.txtFirstNameValue.text = fName
            holder.txtLastNameValue.text = sName
            holder.txtBirthdateValue.text = birthdate
            holder.txtNationValue.text = nation
            holder.txtAddressValue.text = address
            holder.txtPhoneNumberValue.text = phoneNumber.toString()
            holder.txtEmailValue.text = email
            holder.txtMatriculeValue.text = matricule.toString()
            holder.txtTypeValue.text = type.toString()
            holder.txtMarqueValue.text = marque.toString()

            // Définition d'un écouteur de clic pour le bouton de modification
            holder.modifyBtn.setOnClickListener {
                val intent = Intent(context, ModifyMoniteurActivity::class.java)
                intent.putExtra("moniteur_id", Id) // Passage de l'ID du moniteur en tant qu'extra
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }

    inner class MoniteurViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Définition des TextView et du bouton de modification dans le ViewHolder
        val txtIdValue: TextView = itemView.findViewById(R.id.txtIdValue)
        val txtFirstNameValue: TextView = itemView.findViewById(R.id.txtFirstNameValue)
        val txtLastNameValue: TextView = itemView.findViewById(R.id.txtLastNameValue)
        val txtBirthdateValue: TextView = itemView.findViewById(R.id.txtBirthdateValue)
        val txtNationValue: TextView = itemView.findViewById(R.id.txtNationValue)
        val txtAddressValue: TextView = itemView.findViewById(R.id.txtAddressValue)
        val txtPhoneNumberValue: TextView = itemView.findViewById(R.id.txtPhoneNumberValue)
        val txtEmailValue: TextView = itemView.findViewById(R.id.txtEmailValue)
        val txtMatriculeValue: TextView = itemView.findViewById(R.id.txtMatriculeValue)
        val txtTypeValue: TextView = itemView.findViewById(R.id.txtTypeValue)
        val txtMarqueValue: TextView = itemView.findViewById(R.id.txtMarqueValue)
        val modifyBtn: Button = itemView.findViewById(R.id.modifyBtn)

        init {
            // Définition d'un écouteur de clic pour le bouton de modification dans chaque élément de la liste
            modifyBtn.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    cursor?.apply {
                        moveToPosition(position)
                        val Id = getString(getColumnIndexOrThrow("ID"))
                        val intent = Intent(context, ModifyMoniteurActivity::class.java)
                        intent.putExtra("moniteur_id", Id) // Passage de l'ID du moniteur en tant qu'extra
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}
