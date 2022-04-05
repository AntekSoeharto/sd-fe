package com.example.sugardaddy.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Entity.Film
import com.example.sugardaddy.R
import com.squareup.picasso.Picasso

class FilmRecommendedAdapter (private val listFilm: ArrayList<Film>) : RecyclerView.Adapter<FilmRecommendedAdapter.ListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_film, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (id, judul, rating, tanggalTerbit, actor, sinopsis, filmType, releaseType, duration, image, imgBackground) = listFilm[position]
        Picasso.get().load(image).into(holder.imgPhoto)
        holder.tvName.text = judul
        holder.tvDescription.text = sinopsis
    }

    override fun getItemCount(): Int = listFilm.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_title)
        var tvDescription: TextView = itemView.findViewById(R.id.tv_item_film_description)
    }
}