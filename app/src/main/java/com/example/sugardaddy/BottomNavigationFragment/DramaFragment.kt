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
import com.example.sugardaddy.Drama
import com.example.sugardaddy.R

class DramaFragment : Fragment(){

    private lateinit var tvRecommendedDramas: RecyclerView
    private lateinit var tvHottestDramas: RecyclerView
    private val list = ArrayList<Drama>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drama, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvRecommendedDramas = view.findViewById(R.id.tv_recommended_drama)
        tvRecommendedDramas.setHasFixedSize(true)

        tvHottestDramas= view.findViewById(R.id.tv_hot_drama)
        tvHottestDramas.setHasFixedSize(true)

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
        tvRecommendedDramas.layoutManager = LinearLayoutManager(activity)
        val DramaRecommendedAdapter = DramaRecommendedAdapter(list)
        tvRecommendedDramas.adapter = DramaRecommendedAdapter

        tvHottestDramas.layoutManager=LinearLayoutManager(activity)
        val DramaHotAdapter = DramaHotAdapter(list)
        tvHottestDramas.adapter = DramaHotAdapter
    }
}