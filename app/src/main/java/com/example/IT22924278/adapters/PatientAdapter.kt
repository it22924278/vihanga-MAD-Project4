package com.example.IT22924278.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.IT22924278.PatientViewHolder
import com.example.IT22924278.R
import com.example.IT22924278.database.MainActivityData
import com.example.IT22924278.database.PatientRepository
import com.example.IT22924278.database.Patient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PatientAdapter (
    private val items: List<Patient>,
    private val repository: PatientRepository,
    private val viewModel: MainActivityData
) : RecyclerView.Adapter<PatientViewHolder>() {

    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)
        context = parent.context

        return PatientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.pbtn.text = items[position].name
        holder.pName.text = items[position].age
        holder.pDoctor.text = items[position].doctor
        holder.pDelete.setOnClickListener {
            val isChecked = holder.pbtn.isChecked

            if (isChecked) {
                CoroutineScope(Dispatchers.IO).launch {
                    repository.delete(items[position])
                    val data = repository.getAllPatient()
                    withContext(Dispatchers.Main) {
                        viewModel.setData(data)
                    }
                }
            } else
                Toast.makeText(context, "Select patient to delete", Toast.LENGTH_LONG).show()
        }
    }
}