package com.example.sugardaddy.BottomNavigationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Adapter.NewsAdapter
import com.example.sugardaddy.Entity.News
import com.example.sugardaddy.R

class NewsFragment : Fragment() {
    private lateinit var rvNews: RecyclerView
    private val listNews: ArrayList<News> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvNews = view.findViewById(R.id.rv_list_news)
        rvNews.setHasFixedSize(true)
//        listNews = getData()

//        rvListFilms = view.findViewById(R.id.rv_list_film)
//        rvListFilms.setHasFixedSize(true)
//
//        listDrama.addAll(listDramas)
//        listFilm.addAll(listFilms)
        listNews.addAll(listNews)
        showRecyclerList()
    }

    private val news: ArrayList<News>
        get() {
            val dataTitle = resources.getStringArray(R.array.array_news_title)
            val dataAuthor = resources.getStringArray(R.array.array_news_author)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo_news)
            val listNews = ArrayList<News>()
            for (i in dataTitle.indices) {
                val news = News(dataTitle[i], dataAuthor[i], dataPhoto.getResourceId(i, -1), "", "", "", 0,"")
                listNews.add(news)
            }
            return listNews
        }

    private fun showRecyclerList() {
        val newsAdapter = activity?.let { NewsAdapter(it, listNews) }
        rvNews.adapter = newsAdapter
        rvNews.adapter?.notifyDataSetChanged()
        rvNews.layoutManager = LinearLayoutManager(activity)
    }
}