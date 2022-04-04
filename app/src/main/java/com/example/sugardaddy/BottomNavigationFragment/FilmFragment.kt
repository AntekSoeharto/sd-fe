package com.example.sugardaddy.BottomNavigationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Adapter.DramaHotAdapter
import com.example.sugardaddy.Adapter.DramaRecommendedAdapter
import com.example.sugardaddy.Adapter.FilmHotAdapter
import com.example.sugardaddy.Adapter.FilmRecommendedAdapter
import com.example.sugardaddy.Drama
import com.example.sugardaddy.Film
import com.example.sugardaddy.R

class FilmFragment : Fragment() {
    private lateinit var tvRecommendedFilms: RecyclerView
    private lateinit var tvHottestFilms: RecyclerView
    private val list = ArrayList<Film>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvRecommendedFilms = view.findViewById(R.id.tv_recommended_film)
        tvRecommendedFilms.setHasFixedSize(true)

        tvHottestFilms= view.findViewById(R.id.tv_hot_film)
        tvHottestFilms.setHasFixedSize(true)



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
        tvRecommendedFilms.layoutManager = LinearLayoutManager(activity)
        val FilmRecommendedAdapter = FilmRecommendedAdapter(list)
        tvRecommendedFilms.adapter = FilmRecommendedAdapter

        tvHottestFilms.layoutManager= LinearLayoutManager(activity)
        val FilmHotAdapter = FilmHotAdapter(list)
        tvHottestFilms.adapter = FilmHotAdapter
    }
}