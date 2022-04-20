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
import com.example.sugardaddy.Entity.News
import com.example.sugardaddy.NewsDetailActivity
import com.example.sugardaddy.R
import com.example.sugardaddy.SignInActivity
import com.squareup.picasso.Picasso

class NewsAdapter(private val context: Context, private val listNews: ArrayList<News>) : RecyclerView.Adapter<NewsAdapter.ListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_news, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val news = listNews[position]
        val (title, author, image, description, publishedDate, link, rank, country) = listNews[position]
        Picasso.get().load(image).into(holder.imgPhoto)
        holder.tvTitle.text = title
        holder.tvAuthor.text = author

        holder.itemView.setOnClickListener{
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(NewsDetailActivity.INTENT_PARCELABLE, news)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listNews.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo_news)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        var tvAuthor: TextView = itemView.findViewById(R.id.tv_item_author)
    }
}