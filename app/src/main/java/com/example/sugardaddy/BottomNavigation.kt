package com.example.sugardaddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Adapter.CommentsAdapter
import com.example.sugardaddy.Adapter.SearchAdapter
import com.example.sugardaddy.BottomNavigationFragment.*
import com.example.sugardaddy.Entity.Film
import com.example.sugardaddy.databinding.ActivityBottomNavigationBinding
import com.example.sugardaddy.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class BottomNavigation : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val dramaFragment = DramaFragment()
    private val filmFragment = FilmFragment()
    private val myListFragment = MyListFragment()
    private val myAccountFragment = MyAccountFragment()
    private val newsFragment = NewsFragment()

    private lateinit var binding: ActivityBottomNavigationBinding
    private lateinit var buttonNavView : BottomNavigationView
    private val searchFilm =  ArrayList<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchFilm.clear()
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)

        setContentView(binding.root)
        currentFragment(dramaFragment)

        buttonNavView = binding.bottomNavigation

        supportActionBar?.hide()

        buttonNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_drama -> {
                    currentFragment(dramaFragment)
                    binding.svSearch.visibility = View.VISIBLE
//                    binding.dramaFilmList.visibility = View.GONE
                }
                R.id.ic_film -> {
                    currentFragment(filmFragment)
                    binding.svSearch.visibility = View.VISIBLE
//                    binding.dramaFilmList.visibility = View.GONE
                }
                R.id.ic_my_list -> {
                    currentFragment(myListFragment)
                    binding.svSearch.visibility = View.GONE
//                    binding.dramaFilmList.visibility = View.GONE
                }
                R.id.ic_my_account -> {
                    currentFragment(myAccountFragment)
                    binding.svSearch.visibility = View.GONE
//                    binding.dramaFilmList.visibility = View.GONE
                }
                R.id.ic_news -> {
                    currentFragment(newsFragment)
                    binding.svSearch.visibility = View.GONE
//                    binding.dramaFilmList.visibility = View.GONE
                }
            }
            true
        }
        binding.svSearch.setOnQueryTextListener(this)

    }

    private fun currentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_cntainer, fragment)
            commit()
        }
    }

    fun getAllFilm(query: String){
//        Log.e("search ", "Masuk Sini")
//        Log.e("search ", "$text")
        val client = AsyncHttpClient()
//        val params = RequestParams()
//        params.put("search", query)
        val params = "search=" + query
        val url = "http://10.0.2.2:9090/search?" + params
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)

                try {
                    searchFilm.clear()
                    val responseObject = JSONObject(result)
                    val arrayFilm = responseObject.getJSONArray("data")
                    Log.e("List array Object", "${arrayFilm.length()}")

                    for (i in 0 until arrayFilm.length()) {

                        val jsonObject = arrayFilm.getJSONObject(i)
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
                        val film = Film(id, judul, rating, tanggalTerbit, actor, sinopsis, genre, filmType, releaseType, duration, image, imgBackground)
                        searchFilm.add(film)
                        Log.e("image ", "$image")
                        Log.e("List Film", "${searchFilm.size}")
                    }

                    val intent = Intent(this@BottomNavigation, SearchActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("listFilm", searchFilm)
                    startActivity(intent)

                } catch (e: Exception){
                    Toast.makeText(this@BottomNavigation, e.message, Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@BottomNavigation, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        getAllFilm(query)
        return false
    }

    override fun onQueryTextChange(query: String): Boolean {
        return false
    }


}