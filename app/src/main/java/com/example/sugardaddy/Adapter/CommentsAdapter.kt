package com.example.sugardaddy.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Entity.Comments
import com.example.sugardaddy.R

class CommentsAdapter (private val listComments: ArrayList<Comments> ): RecyclerView.Adapter<CommentsAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_comments, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (temp, username, email, comment) = listComments[position]
        holder.tvUsername.text = username
        holder.tvComments.text = comment
    }

    override fun getItemCount(): Int = listComments.size

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvUsername: TextView = itemView.findViewById(R.id.tv_text_comments)
        var tvComments: TextView = itemView.findViewById(R.id.tv_comments_description)
    }
}

