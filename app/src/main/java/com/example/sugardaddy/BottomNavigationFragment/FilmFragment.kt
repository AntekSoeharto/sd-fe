package com.example.sugardaddy.BottomNavigationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextSwitcher
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Adapter.FilmHotAdapter
import com.example.sugardaddy.Adapter.FilmRecommendedAdapter
import com.example.sugardaddy.Entity.Film
import com.example.sugardaddy.R
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class FilmFragment : Fragment() {
    private lateinit var rvRecommendedFilms: RecyclerView
    private lateinit var rvHottestFilms: RecyclerView
    private val listFilmRecommendation = ArrayList<Film>()
    private val listFilmRHottest = ArrayList<Film>()
    private lateinit var tvText: TextView

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

        tvText = view.findViewById(R.id.tv_recommended_video)

        listFilmRecommendation.clear()
        listFilmRHottest.clear()

        getFilmecommendation()
        getFilmHottest()

//        list.addAll(listFilms)
        showRecyclerList()
    }

    private fun getFilmHottest() {
        val client = AsyncHttpClient()
        val url = "http://10.0.2.2:9090/film?film_type=Film&film_display=2"
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)

                try {
                    val responseObject = JSONObject(result)

                    val listFilm = responseObject.getJSONArray("data")

                    for (i in 0 until listFilm.length()) {

                        val jsonObject = listFilm.getJSONObject(i)
                        val id = jsonObject.getInt("Rating")
                        val judul = jsonObject.getString("Judul")
                        val rating = jsonObject.getInt("Rating")
                        val tanggalTerbit = jsonObject.getString("TanggalTerbit")
                        val actor = jsonObject.getString("Actor")
                        val sinopsis = jsonObject.getString("Sinopsis")
                        val filmType = jsonObject.getString("FilmType")
                        val releaseType = jsonObject.getString("ReleaseType")
                        val duration = jsonObject.getInt("Duration")
                        val image = jsonObject.getString("Image")
                        val imgBackground = jsonObject.getString("ImageBackground")
                        val film = Film(id, judul, rating, tanggalTerbit, actor, sinopsis, filmType, releaseType, duration, image, imgBackground)
                        listFilmRHottest.add(film)

                    }

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

    private fun getFilmecommendation() {
        val client = AsyncHttpClient()
        val url = "http://10.0.2.2:9090/film?film_type=Drama&film_display=1"
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)

                try {
                    val responseObject = JSONObject(result)
                    val listFilm = responseObject.getJSONArray("data")

                    for (i in 0 until listFilm.length()) {

                        val jsonObject = listFilm.getJSONObject(i)
                        val id = jsonObject.getInt("Rating")
                        val judul = jsonObject.getString("Judul")
                        val rating = jsonObject.getInt("Rating")
                        val tanggalTerbit = jsonObject.getString("TanggalTerbit")
                        val actor = jsonObject.getString("Actor")
                        val sinopsis = jsonObject.getString("Sinopsis")
                        val filmType = jsonObject.getString("FilmType")
                        val releaseType = jsonObject.getString("ReleaseType")
                        val duration = jsonObject.getInt("Duration")
                        val image = jsonObject.getString("Image")
                        val imgBackground = jsonObject.getString("ImageBackground")
                        val film = Film(id, judul, rating, tanggalTerbit, actor, sinopsis, filmType, releaseType, duration, image, imgBackground)
                        listFilmRecommendation.add(film)

                    }

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

//    private val listFilms: ArrayList<Film>
//        get() {
//            val dataName = resources.getStringArray(R.array.data_name_film)
//            val dataDescription = resources.getStringArray(R.array.data_film_description)
//            val dataPhoto = resources.getStringArray(R.array.data_drama_photo)
//            val listFilm = ArrayList<Film>()
//            for (i in dataName.indices) {
//                val film = Film(dataName[i],dataDescription[i], dataPhoto[i])
//                listFilm.add(film)
//            }
//            return listFilm
//        }
//
    private fun showRecyclerList() {
        rvRecommendedFilms.layoutManager = LinearLayoutManager(activity)
        val FilmRecommendedAdapter = FilmRecommendedAdapter(listFilmRecommendation)
        rvRecommendedFilms.adapter = FilmRecommendedAdapter

        rvHottestFilms.layoutManager= LinearLayoutManager(activity)
        val FilmHotAdapter = FilmHotAdapter(listFilmRHottest)
        rvHottestFilms.adapter = FilmHotAdapter
    }
}