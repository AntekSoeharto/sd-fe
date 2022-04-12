package com.example.sugardaddy.BottomNavigationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Entity.Film
import com.example.sugardaddy.R

class MyListFragment : Fragment() {
    private lateinit var rvListDramas: RecyclerView
//    private val listDrama = ArrayList<Drama>()
    private val listFilm = ArrayList<Film>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvListDramas = view.findViewById(R.id.rv_list_drama)
        rvListDramas.setHasFixedSize(true)

//        rvListFilms = view.findViewById(R.id.rv_list_film)
//        rvListFilms.setHasFixedSize(true)
//
//        listDrama.addAll(listDramas)
//        listFilm.addAll(listFilms)
//        showRecyclerList()
    }

//    private val listDramas: ArrayList<Drama>
//        get() {
//            val dataName = resources.getStringArray(R.array.data_name_drama)
//            val dataDescription = resources.getStringArray(R.array.data_drama_description)
//            val dataPhoto = resources.getStringArray(R.array.data_drama_photo)
//            val listDrama = ArrayList<Drama>()
//            for (i in dataName.indices) {
//                val drama = Drama(dataName[i],dataDescription[i], dataPhoto[i])
//                listDrama.add(drama)
//            }
//            return listDrama
//        }
//
//    private val listFilms: ArrayList<Film>
//        get() {
//            val dataName = resources.getStringArray(R.array.data_name_film)
//            val dataDescription = resources.getStringArray(R.array.data_film_description)
//            val dataPhoto = resources.getStringArray(R.array.data_film_photo)
//            val listFilm = ArrayList<Film>()
//            for (i in dataName.indices) {
//                val film = Film(dataName[i],dataDescription[i], dataPhoto[i])
//                listFilm.add(film)
//            }
//            return listFilm
//        }
//
//    private fun showRecyclerList() {
//        rvListDramas.layoutManager = LinearLayoutManager(activity)
//        val ListDramaAdapter = ListDramaAdapter(listDrama)
//        rvListDramas.adapter = ListDramaAdapter
//
//        rvListFilms.layoutManager= LinearLayoutManager(activity)
//        val ListFilmAdapter = ListFilmAdapter(listFilm)
//        rvListFilms.adapter = ListFilmAdapter
//    }
}