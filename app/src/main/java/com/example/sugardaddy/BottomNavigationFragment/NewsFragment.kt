package com.example.sugardaddy.BottomNavigationFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Adapter.NewsAdapter
import com.example.sugardaddy.Entity.Film
import com.example.sugardaddy.Entity.News
import com.example.sugardaddy.R
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

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


        getNews()
        showRecyclerList()
    }

    private fun getNews() {
        val client = AsyncHttpClient()
        client.addHeader("x-rapidapi-key", "b9ea2f8a0amsh5c48eec5ac3f0ecp1e4fe0jsna6e61a943f02")
        client.addHeader("x-rapidapi-host", "free-news.p.rapidapi.com")
        val url = "https://free-news.p.rapidapi.com/v1/search?q=entertainment&lang=en&page=1&page_size=25"
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)

                try {
                    val responseObject = JSONObject(result)

                    val news = responseObject.getJSONArray("articles")

                    for (i in 0 until news.length()) {

                        val jsonObject = news.getJSONObject(i)
                        val title = jsonObject.getString("title")
                        val author = jsonObject.getString("author")
                        val image = jsonObject.getString("media")
                        val description = jsonObject.getString("summary")
                        val publishedDate = jsonObject.getString("published_date")
                        val link = jsonObject.getString("link")
                        val rank = jsonObject.getInt("rank")
                        val country = jsonObject.getString("country")
                        val temp = News(title, author, image, description, publishedDate, link, rank, country)
                        listNews.add(temp)
                    }
                    rvNews.adapter?.notifyDataSetChanged()
                } catch (e: Exception){
                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }


    private fun showRecyclerList() {
        val newsAdapter = activity?.let { NewsAdapter(it, listNews) }
        rvNews.adapter = newsAdapter
        rvNews.adapter?.notifyDataSetChanged()
        rvNews.layoutManager = LinearLayoutManager(activity)
    }
}