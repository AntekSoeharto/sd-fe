package com.example.sugardaddy.BottomNavigationFragment

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Adapter.DramaHotAdapter
import com.example.sugardaddy.Adapter.DramaRecommendedAdapter
import com.example.sugardaddy.Adapter.ListDramaAdapter
import com.example.sugardaddy.Entity.Drama
import com.example.sugardaddy.R

class DramaFragment : Fragment(){

    private lateinit var rvRecommendedDramas: RecyclerView
    private lateinit var rvHottestDramas: RecyclerView
    private val list = ArrayList<Drama>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drama, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvRecommendedDramas = view.findViewById(R.id.rv_recommended_drama)
        rvRecommendedDramas.setHasFixedSize(true)

        rvHottestDramas= view.findViewById(R.id.rv_hot_drama)
        rvHottestDramas.setHasFixedSize(true)

        list.clear()

        list.addAll(listDramas)
        showRecyclerList()
    }

    private val listDramas: ArrayList<Drama>
        get() {
            val dataName = resources.getStringArray(R.array.data_name_drama)
            val dataDescription = resources.getStringArray(R.array.data_drama_description)
            val dataPhoto = resources.getStringArray(R.array.data_drama_photo)
            val listDrama = ArrayList<Drama>()
            for (i in dataName.indices) {
                val drama = Drama(dataName[i],dataDescription[i], dataPhoto[i])
                listDrama.add(drama)
            }
            return listDrama
        }

    private fun showRecyclerList() {

        rvRecommendedDramas.layoutManager = LinearLayoutManager(activity)
        val DramaRecommendedAdapter = DramaRecommendedAdapter(list)
        rvRecommendedDramas.adapter = DramaRecommendedAdapter

        rvHottestDramas.layoutManager=LinearLayoutManager(activity)
        val DramaHotAdapter = DramaHotAdapter(list)
        rvHottestDramas.adapter = DramaHotAdapter

        DramaRecommendedAdapter.setOnItemClickCallback(object : DramaRecommendedAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Drama) {
                showSelectedHero(data)
            }
        })

        DramaHotAdapter.setOnItemClickCallback(object : DramaHotAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Drama) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(drama: Drama) {
        Toast.makeText(activity, "Kamu memilih " + drama.name, Toast.LENGTH_SHORT).show()
    }
}