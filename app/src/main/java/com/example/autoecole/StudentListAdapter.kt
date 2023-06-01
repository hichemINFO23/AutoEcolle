package com.example.autoecole

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.autoecole.R

class StudentListAdapter(private val context: Context, private val studentList: List<ManqueActivity.Student>) :
    BaseAdapter() {

    override fun getCount(): Int {
        return studentList.size
    }

    override fun getItem(position: Int): Any {
        return studentList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_student, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val student = getItem(position) as ManqueActivity.Student
        viewHolder.idTextView.text = student.id.toString()
        viewHolder.fnameTextView.text = student.fname
        viewHolder.snameTextView.text = student.sname
        viewHolder.manqueTextView.text = student.manque.toString()

        return view
    }

    private class ViewHolder(view: View) {
        val idTextView: TextView = view.findViewById(R.id.idTextView)
        val fnameTextView: TextView = view.findViewById(R.id.fnameTextView)
        val snameTextView: TextView = view.findViewById(R.id.snameTextView)
        val manqueTextView: TextView = view.findViewById(R.id.manqueTextView)
    }
}


