package com.example.sugardaddy.BottomNavigationFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Adapter.MyListAdapter
import com.example.sugardaddy.Entity.Film
import com.example.sugardaddy.Helper.MappingHelper
import com.example.sugardaddy.R
import com.example.sugardaddy.db.FilmHelper
import kotlinx.coroutines.runBlocking

class MyListFragment : Fragment() {
    private lateinit var rvMyList: RecyclerView
//    private val listDrama = ArrayList<Drama>()
    private lateinit var listFilm: ArrayList<Film>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMyList = view.findViewById(R.id.rv_list_drama)
        rvMyList.setHasFixedSize(true)
        listFilm = getData()

//        rvListFilms = view.findViewById(R.id.rv_list_film)
//        rvListFilms.setHasFixedSize(true)
//
//        listDrama.addAll(listDramas)
//        listFilm.addAll(listFilms)
        showRecyclerList()
    }
    private fun getData(): ArrayList<Film>{
        var listFilm: ArrayList<Film> = ArrayList()
        val filmHelper = activity?.let { FilmHelper.getInstance(it.applicationContext) }
        if (filmHelper != null) {
            filmHelper.open()
        }
        val cursor = filmHelper?.queryAll()
        listFilm = MappingHelper.mapFilmCursorToArrayList(cursor)
        if (filmHelper != null) {
            filmHelper.close()
        }
        Log.e("List  ", "${listFilm.size}")
        return listFilm
    }


    private fun showRecyclerList() {
        val MyListAdapter = activity?.let { MyListAdapter(it, listFilm) }
        rvMyList.adapter = MyListAdapter
        rvMyList.adapter?.notifyDataSetChanged()
        rvMyList.layoutManager = LinearLayoutManager(activity)
    }
}