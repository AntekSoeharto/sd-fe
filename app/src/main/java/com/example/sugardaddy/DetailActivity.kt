package com.example.sugardaddy

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Adapter.CommentsAdapter
import com.example.sugardaddy.Entity.Comments
import com.example.sugardaddy.Entity.Film
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
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion._ID
import com.example.sugardaddy.db.FilmHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

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

    private lateinit var rvComments: RecyclerView
    private val list = ArrayList<Comments>()

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
                val values = ContentValues()
                values.put(_ID, detailDrama.ID)
                values.put(JUDUL, detailDrama.ID)
                values.put(RATING, detailDrama.ID)
                values.put(TANGGAL_TERBIT, detailDrama.ID)
                values.put(ACTOR, detailDrama.ID)
                values.put(SINOPSIS, detailDrama.ID)
                values.put(GENRE, detailDrama.ID)
                values.put(FILM_TYPE, detailDrama.ID)
                values.put(RELEASE_TYPE, detailDrama.ID)
                values.put(DURATION, detailDrama.ID)
                values.put(IMAGE, detailDrama.ID)
                values.put(IMG_BACKGRUOND, detailDrama.ID)

                val status = filmHelper.insert(values)
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

        if (detailDrama != null){
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

        list.addAll(listComments)
        showRecyclerList()
    }

    private val listComments: ArrayList<Comments>
        get() {
            val dataUsername = resources.getStringArray(R.array.array_username_comments)
            val dataComments = resources.getStringArray(R.array.array_comments)
            val listComments = ArrayList<Comments>()
            for (i in dataUsername.indices) {
                val comments = Comments(dataUsername[i],dataComments[i])
                listComments.add(comments)
            }
            return listComments
        }
    private fun showRecyclerList() {
        rvComments.layoutManager = LinearLayoutManager(this)
        val CommentsAdapter = CommentsAdapter(list)
        rvComments.adapter = CommentsAdapter
    }
}



