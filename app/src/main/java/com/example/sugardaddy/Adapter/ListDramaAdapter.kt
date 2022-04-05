package com.example.sugardaddy.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Entity.Drama
import com.example.sugardaddy.Entity.Film
import com.example.sugardaddy.R
import com.squareup.picasso.Picasso

class ListDramaAdapter(private val listDrama: ArrayList<Drama>) : RecyclerView.Adapter<ListDramaAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: ListDramaAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: ListDramaAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_drama, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listDrama[position]
        Picasso.get().load(photo).into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listDrama[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int = listDrama.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_title)
        var tvDescription: TextView = itemView.findViewById(R.id.tv_item_drama_description)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Drama)
    }
}