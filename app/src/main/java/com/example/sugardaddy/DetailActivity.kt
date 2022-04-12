package com.example.sugardaddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Adapter.CommentsAdapter
import com.example.sugardaddy.Entity.Comments
import com.example.sugardaddy.Entity.Film
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

    private lateinit var rvComments: RecyclerView
    private val list = ArrayList<Comments>()

    companion object{
        const val INTENT_PARCELABLE = "OBJECT_INTERN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailFilm = intent.getParcelableExtra<Film>(INTENT_PARCELABLE)
        val detailDrama = intent.getParcelableExtra<Film>(INTENT_PARCELABLE)

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