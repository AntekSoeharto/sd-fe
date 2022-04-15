package com.example.sugardaddy

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Adapter.CommentsAdapter
import com.example.sugardaddy.Entity.Comments
import com.example.sugardaddy.Helper.UserSingleton
import com.example.sugardaddy.Entity.Film
import com.example.sugardaddy.Entity.User
import com.example.sugardaddy.Helper.MappingHelper
import com.example.sugardaddy.db.DatabaseContract
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion.ACTOR
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion.DURATION
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion.FILM_TYPE
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion.GENRE
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion.IMAGE
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion.IMG_BACKGRUOND
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion.JUDUL
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion.RATING
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion.RELEASE_TYPE
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion.SINOPSIS
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion.TANGGAL_TERBIT
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion.TEMP_ID
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion._ID
import com.example.sugardaddy.db.FilmHelper
import com.example.sugardaddy.db.UserHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import com.squareup.picasso.Picasso
import cz.msebera.android.httpclient.Header
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception
import java.net.URLEncoder
import kotlin.properties.Delegates

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvJudul: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvType: TextView
    private lateinit var tvSinopsis: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvCast: TextView
    private lateinit var tvReleaseDate: TextView
    private lateinit var tvDuration: TextView
    private lateinit var image: ImageView
    private lateinit var imgBackground: ImageView
    private lateinit var filmHelper: FilmHelper
    private var id by Delegates.notNull<Int>()
    private lateinit var btnSend: Button
    private lateinit var etComments: EditText

    private lateinit var rvComments: RecyclerView
    private val listComment = ArrayList<Comments>()

    private lateinit var btnAddList: View

    companion object{
        const val INTENT_PARCELABLE = "OBJECT_INTERN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailFilm = intent.getParcelableExtra<Film>(INTENT_PARCELABLE)
        val detailDrama = intent.getParcelableExtra<Film>(INTENT_PARCELABLE)
        filmHelper = FilmHelper(this)
        filmHelper = FilmHelper.getInstance(applicationContext)
        filmHelper.open()




        btnAddList = findViewById(R.id.fab_addList)
        btnAddList.setOnClickListener {
            if(detailDrama != null){
                addToList(detailDrama)
            }else if(detailFilm != null){
                addToList(detailFilm)
            }
        }

        tvJudul = findViewById(R.id.detail_movie_title)
        tvRating = findViewById(R.id.rate)
        tvType = findViewById(R.id.tv_detail_type)
        tvSinopsis = findViewById(R.id.detail_movie_desc)
        tvCast = findViewById(R.id.tv_detail_cast)
        tvReleaseDate = findViewById(R.id.tv_release_date)
        tvDuration = findViewById(R.id.tv_duration)
        image = findViewById(R.id.detail_movie_img)
        imgBackground = findViewById(R.id.detail_movie_cover)
        tvGenre = findViewById(R.id.tv_genre)
        btnSend = findViewById(R.id.btn_send)
        etComments = findViewById(R.id.et_comments)

        btnSend.setOnClickListener(this)

        if (detailDrama != null){
            Log.e("ID ", "${detailDrama.ID}")
            id = detailDrama.ID
            tvJudul.text = detailDrama.judul
            tvRating.text = detailDrama.rating.toString()
            tvType.text = detailDrama.filmType
            tvSinopsis.text = detailDrama.sinopsis
            tvGenre.text = detailDrama.genre
            tvCast.text = detailDrama.actor
            tvReleaseDate.text = detailDrama.tanggalTerbit
            tvDuration.text = detailDrama.duration.toString()
            Picasso.get().load(detailDrama.image).into(image)
            Picasso.get().load(detailDrama.imgBackground).into(imgBackground)

        }else if (detailFilm != null){
            id = detailFilm.ID
            tvJudul.text = detailFilm.judul
            tvRating.text = detailFilm.rating.toString()
            tvType.text = detailFilm.filmType
            tvSinopsis.text = detailFilm.sinopsis
            tvGenre.text = detailFilm.genre
            tvCast.text = detailFilm.actor
            tvReleaseDate.text = detailFilm.tanggalTerbit
            tvDuration.text = detailFilm.duration.toString()
            Picasso.get().load(detailFilm.image).into(image)
            Picasso.get().load(detailFilm.imgBackground).into(imgBackground)
        }

        rvComments = findViewById(R.id.rv_comments)
        rvComments.setHasFixedSize(true)
        getComment()

//        list.addAll()
        showRecyclerList()
    }


    private fun showRecyclerList() {
        rvComments.layoutManager = LinearLayoutManager(this)
        val CommentsAdapter = CommentsAdapter(listComment)
        rvComments.adapter = CommentsAdapter
    }

    private fun getAllFilm(): ArrayList<Film>{
        var listFilm: ArrayList<Film> = ArrayList()
        val filmHelper = FilmHelper.getInstance(applicationContext)
        filmHelper.open()
        val cursor = filmHelper.queryAll()
        listFilm = MappingHelper.mapFilmCursorToArrayList(cursor)
        filmHelper.close()
        Log.e("List  ", "${listFilm.size}")
        return listFilm
    }

    private fun getComment() {
        listComment.clear()
        val client = AsyncHttpClient()

        val url = "http://10.0.2.2:9090/comment?film_id=" + id.toString()
//        Log.e("DeBug", "$url")
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
                        val commentId = jsonObject.getInt("ID")
                        val username = jsonObject.getString("Username")
                        val email = jsonObject.getString("Email")
                        val comment = jsonObject.getString("Comment")
                        val commentClass = Comments(commentId, username, email, comment)
                        listComment.add(commentClass)
                    }
                    rvComments.adapter?.notifyDataSetChanged()

                } catch (e: Exception){
                    Log.e("Error", "$e")
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
                Log.e("Error", "$statusCode")
            }

        })
    }

    private fun addToList(film: Film){
        var listFilm: ArrayList<Film> = getAllFilm()

        Log.e("Size List ", "${listFilm.size}")

        val values = ContentValues()
        values.put(TEMP_ID, film.ID)
        values.put(JUDUL, film.judul)
        values.put(RATING, film.rating)
        values.put(TANGGAL_TERBIT, film.tanggalTerbit)
        values.put(ACTOR, film.actor)
        values.put(SINOPSIS, film.sinopsis)
        values.put(GENRE, film.genre)
        values.put(FILM_TYPE, film.filmType)
        values.put(RELEASE_TYPE, film.releaseType)
        values.put(DURATION, film.duration)
        values.put(IMAGE, film.image)
        values.put(IMG_BACKGRUOND, film.imgBackground)

//        if(listFilmMyList.size == 0){
//            val status = filmHelper.insert(values)
//            if(status > -1){
//                Toast.makeText(this@DetailActivity, "Film/Drama Added to  List", Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(this@DetailActivity, "Film/Drama Already Exist in Your List", Toast.LENGTH_SHORT).show()
//            }
//        }

        var isExist = false
        for (i in 0 until listFilm.size){
            if(listFilm.get(i).ID == film.ID) {
                isExist = true
            }
        }
        if(isExist == false){
            val status = filmHelper.insert(values)
            if(status > -1){
                Toast.makeText(this@DetailActivity, "Film/Drama Added to  List", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@DetailActivity, "Failed Added Film/Drama to List", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this@DetailActivity, "Film/Drama Already Exist in Your List", Toast.LENGTH_SHORT).show()
        }
    }

    fun postComment(commentTemp: String){
        val client = AsyncHttpClient()
        val parameter = RequestParams()
        parameter.put("user_id", UserSingleton.getUserid())
        parameter.put("film_id", id)
        parameter.put("comment", commentTemp)
//        var param: String = "user_id=" + UserSingleton.getUserid() + "&film_id=" + id + "&comment=" + commentTemp
        Log.e("User Id by Detail ", "${UserSingleton.getUserid()}")
        val url = "http://10.0.2.2:9090/comment"
//        val urlFix = URLEncoder.encode(url, "UTF-8")
        Log.e("param ", "$url")
        client.post(url, parameter, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)

                try {
                    val responseObject = JSONObject(result)
                    val status = responseObject.getInt("status")
                    Log.e("status ", "$status")

                    if (status == 200){
                        Toast.makeText(this@DetailActivity, "Success Add Comment", Toast.LENGTH_SHORT)
                        etComments.text.clear()
                        getComment()

                    }else{
                        Toast.makeText(this@DetailActivity, "Failed Add Comment", Toast.LENGTH_SHORT)
                    }

                } catch (e: Exception){
                    Toast.makeText(this@DetailActivity, e.message, Toast.LENGTH_SHORT).show()
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
//                Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_send -> {
                var commentTemp: String = etComments.text.toString()
                Log.e("Comment ", "$commentTemp")
                postComment(commentTemp)
            }
        }
    }
}



