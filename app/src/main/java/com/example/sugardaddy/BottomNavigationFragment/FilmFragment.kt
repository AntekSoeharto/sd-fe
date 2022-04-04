package com.example.sugardaddy.BottomNavigationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Adapter.FilmHotAdapter
import com.example.sugardaddy.Adapter.FilmRecommendedAdapter
import com.example.sugardaddy.Entity.Film
import com.example.sugardaddy.R

class FilmFragment : Fragment() {
    private lateinit var rvRecommendedFilms: RecyclerView
    private lateinit var rvHottestFilms: RecyclerView
    private val list = ArrayList<Film>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvRecommendedFilms = view.findViewById(R.id.rv_recommended_film)
        rvRecommendedFilms.setHasFixedSize(true)

        rvHottestFilms= view.findViewById(R.id.rv_hot_film)
        rvHottestFilms.setHasFixedSize(true)

        list.addAll(listFilms)
        showRecyclerList()
    }

    private val listFilms: ArrayList<Film>
        get() {
            val dataName = resources.getStringArray(R.array.data_name_film)
            val dataDescription = resources.getStringArray(R.array.data_film_description)
            val dataPhoto = resources.getStringArray(R.array.data_drama_photo)
            val listFilm = ArrayList<Film>()
            for (i in dataName.indices) {
                val film = Film(dataName[i],dataDescription[i], dataPhoto[i])
                listFilm.add(film)
            }
            return listFilm
        }

    private fun showRecyclerList() {
        rvRecommendedFilms.layoutManager = LinearLayoutManager(activity)
        val FilmRecommendedAdapter = FilmRecommendedAdapter(list)
        rvRecommendedFilms.adapter = FilmRecommendedAdapter

        rvHottestFilms.layoutManager= LinearLayoutManager(activity)
        val FilmHotAdapter = FilmHotAdapter(list)
        rvHottestFilms.adapter = FilmHotAdapter
    }
}