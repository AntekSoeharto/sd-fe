package com.example.sugardaddy.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.DetailActivity
import com.example.sugardaddy.Entity.Film
import com.example.sugardaddy.R
import com.example.sugardaddy.SignInActivity
import com.squareup.picasso.Picasso

class DramaHotAdapter(private val context: Context, private val listDrama: ArrayList<Film>) : RecyclerView.Adapter<DramaHotAdapter.ListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_drama, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val listDramaHot = listDrama[position]
        val (id, judul, rating, tanggalTerbit, actor, sinopsis, genre, filmType, releaseType, duration, image, imgBackground) = listDrama[position]
        Picasso.get().load(image).into(holder.imgPhoto)
        holder.tvName.text = judul
        holder.tvDescription.text = sinopsis


        holder.itemView.setOnClickListener{
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.INTENT_PARCELABLE, listDramaHot)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listDrama.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_title)
        var tvDescription: TextView = itemView.findViewById(R.id.tv_item_drama_description)
    }
}