package com.example.sugardaddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Adapter.CommentsAdapter
import com.example.sugardaddy.Adapter.SearchAdapter
import com.example.sugardaddy.Entity.Film

class SearchActivity : AppCompatActivity() {

    private var listFilm: ArrayList<Film> = ArrayList()

    private lateinit var rvSearch: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        listFilm.clear()

        rvSearch = findViewById(R.id.rv_search)
        rvSearch.setHasFixedSize(true)
        getData()
        showRecyclerList()
        getData()
    }

    private fun getData() {
        val listFilmtemp = intent.getSerializableExtra( "listFilm" )
        listFilm = listFilmtemp as ArrayList<Film>
        rvSearch.adapter?.notifyDataSetChanged()
    }

    private fun showRecyclerList() {
        rvSearch.layoutManager = LinearLayoutManager(this)
        val SearchAdapter = SearchAdapter(applicationContext, listFilm)
        rvSearch.adapter = SearchAdapter
    }
}