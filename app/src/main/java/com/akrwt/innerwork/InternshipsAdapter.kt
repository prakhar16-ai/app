package com.akrwt.innerwork

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InternshipsAdapter(private val context:Context, private var arrayList:ArrayList<Internship>):RecyclerView.Adapter<InternshipsAdapter.InternshipViewHolder>() {
    inner class InternshipViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val c_image:ImageView = itemView.findViewById(R.id.c_image)
        val title:TextView = itemView.findViewById(R.id.c_title)
        val cName:TextView = itemView.findViewById(R.id.c_name)
        val stipend:TextView = itemView.findViewById(R.id.c_stipend)
        val location:TextView = itemView.findViewById(R.id.c_location)
        val duration:TextView = itemView.findViewById(R.id.c_duration)
        val jobOrInternship:TextView = itemView.findViewById(R.id.c_job_or_internship)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InternshipViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return InternshipViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
    fun setFilter(listItem: List<Internship>) {
        arrayList = ArrayList()
        arrayList.addAll(listItem)
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: InternshipViewHolder, position: Int) {
        val current = arrayList[position]
        holder.c_image.setImageResource(current.image)
        holder.title.text = current.title
        holder.cName.text = current.name
        holder.stipend.text = current.stipend
        holder.location.text = current.location
        holder.duration.text = current.duration
        holder.jobOrInternship.text = current.jobOrInternship
    }
}