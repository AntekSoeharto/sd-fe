package com.example.sugardaddy.BottomNavigationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Drama
import com.example.sugardaddy.ListDramaAdapter
import com.example.sugardaddy.R

class DramaFragment : Fragment(){

    private lateinit var tvDramas: RecyclerView
    private val list = ArrayList<Drama>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drama, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvDramas = view.findViewById(R.id.tv_recommended_drama)
        tvDramas.setHasFixedSize(true)

        list.addAll(listDramas)
        showRecyclerList()
    }

    private val listDramas: ArrayList<Drama>
        get() {
            val dataName = resources.getStringArray(R.array.data_name_drama)
            val dataDescription = resources.getStringArray(R.array.data_drama_description)
            val dataPhoto = resources.obtainTypedArray(R.array.data_drama_photo)
            val listDrama = ArrayList<Drama>()
            for (i in dataName.indices) {
                val drama = Drama(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
                listDrama.add(drama)
            }
            return listDrama
        }

    private fun showRecyclerList() {
        tvDramas.layoutManager = LinearLayoutManager(activity)
        val listDramaAdapter = ListDramaAdapter(list)
        tvDramas.adapter = listDramaAdapter
    }
}