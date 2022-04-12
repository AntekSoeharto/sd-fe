package com.example.sugardaddy.BottomNavigationFragment

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Adapter.DramaHotAdapter
import com.example.sugardaddy.Adapter.DramaRecommendedAdapter
import com.example.sugardaddy.Entity.Film
import com.example.sugardaddy.R
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header

import org.json.JSONObject
import java.lang.Exception

class DramaFragment : Fragment(){

    private lateinit var rvRecommendedDramas: RecyclerView
    private lateinit var rvHottestDramas: RecyclerView
    private val listDramaRecommendation = ArrayList<Film>()
    private val listDramaHottest = ArrayList<Film>()

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTERN"
    }

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

        listDramaHottest.clear()
        listDramaRecommendation.clear()

//        listDramaRecommendation.addAll(listDramas)
//        listDramaHottest.addAll(listDramas)

        getDramaRecommendation()
        getDramaHottest()

        showRecyclerList()
    }

    private fun getDramaHottest() {
        val client = AsyncHttpClient()
        val url = "http://10.0.2.2:9090/film?film_type=Drama&film_display=2"
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)

                try {
                    val responseObject = JSONObject(result)
                    val listDrama = responseObject.getJSONArray("data")

                    for (i in 0 until listDrama.length()) {

                        val jsonObject = listDrama.getJSONObject(i)
                        val id = jsonObject.getInt("ID")
                        val judul = jsonObject.getString("Judul")
                        val rating = jsonObject.getDouble("Rating")
                        val tanggalTerbit = jsonObject.getString("TanggalTerbit")
                        val actor = jsonObject.getString("Actor")
                        val sinopsis = jsonObject.getString("Sinopsis")
                        val genre = jsonObject.getString("Genre")
                        val filmType = jsonObject.getString("FilmType")
                        val releaseType = jsonObject.getString("ReleaseType")
                        val duration = jsonObject.getInt("Duration")
                        val image = jsonObject.getString("Image")
                        val imgBackground = jsonObject.getString("ImageBackground")
                        val drama = Film(id, judul, rating, tanggalTerbit, actor, sinopsis, genre, filmType, releaseType, duration, image, imgBackground)
                        listDramaHottest.add(drama)

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

    private fun getDramaRecommendation() {
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
                    val listDrama = responseObject.getJSONArray("data")

                    for (i in 0 until listDrama.length()) {

                        val jsonObject = listDrama.getJSONObject(i)
                        val id = jsonObject.getInt("ID")
                        val judul = jsonObject.getString("Judul")
                        val rating = jsonObject.getDouble("Rating")
                        val tanggalTerbit = jsonObject.getString("TanggalTerbit")
                        val actor = jsonObject.getString("Actor")
                        val sinopsis = jsonObject.getString("Sinopsis")
                        val genre = jsonObject.getString("Genre")
                        val filmType = jsonObject.getString("FilmType")
                        val releaseType = jsonObject.getString("ReleaseType")
                        val duration = jsonObject.getInt("Duration")
                        val image = jsonObject.getString("Image")
                        val imgBackground = jsonObject.getString("ImageBackground")
                        val drama = Film(id, judul, rating, tanggalTerbit, actor, sinopsis, genre, filmType, releaseType, duration, image, imgBackground)
                        listDramaRecommendation.add(drama)

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

//    private val listDramas: ArrayList<Film>
//        get() {
//            val dataName = resources.getStringArray(R.array.data_name_drama)
//            val dataDescription = resources.getStringArray(R.array.data_drama_description)
//            val dataPhoto = resources.getStringArray(R.array.data_drama_photo)
//            val listDrama = ArrayList<Film>()
//            for (i in dataName.indices) {
//                val drama = Film(dataName[i],dataDescription[i], dataPhoto[i])
//                listDrama.add(drama)
//            }
//            return listDrama
//        }
//
    private fun showRecyclerList() {
        rvRecommendedDramas.layoutManager = LinearLayoutManager(activity)
        val DramaRecommendedAdapter = activity?.let { DramaRecommendedAdapter(it, listDramaRecommendation) }
        rvRecommendedDramas.adapter = DramaRecommendedAdapter

        rvHottestDramas.layoutManager=LinearLayoutManager(activity)
        val DramaHotAdapter = activity?.let { DramaHotAdapter(it, listDramaHottest) }
        rvHottestDramas.adapter = DramaHotAdapter
    }
}