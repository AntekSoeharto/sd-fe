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
import com.example.sugardaddy.Adapter.FilmHotAdapter
import com.example.sugardaddy.Adapter.SearchAdapter
import com.example.sugardaddy.Entity.Film

class SearchActivity : AppCompatActivity() {

    private var listFilm: ArrayList<Film> = ArrayList()

    private lateinit var rvSearch: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        rvSearch = findViewById(R.id.rv_search)
        rvSearch.setHasFixedSize(true)
        supportActionBar?.hide()
        getData()
        showRecyclerList()
    }

    private fun getData() {
        listFilm.clear()
        val listFilmtemp = intent.getSerializableExtra( "listFilm" )
        listFilm = listFilmtemp as ArrayList<Film>
        Log.e("List size ", "${listFilm.size}")
        rvSearch.adapter?.notifyDataSetChanged()
    }

    private fun showRecyclerList() {
        val SearchAdapter = SearchAdapter(applicationContext, listFilm)
        rvSearch.adapter = SearchAdapter
        rvSearch.layoutManager = LinearLayoutManager(this)

    }
}