package com.example.sugardaddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sugardaddy.Adapter.CommentsAdapter
import com.example.sugardaddy.Entity.Comments
import com.example.sugardaddy.Entity.Film
import com.example.sugardaddy.db.FilmHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import java.time.temporal.Temporal
import kotlin.properties.Delegates

class DetailMyListActivity : AppCompatActivity() {

    private lateinit var tvJudul: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvType: TextView
    private lateinit var tvSinopsis: TextView
    private lateinit var tvCast: TextView
    private lateinit var tvReleaseDate: TextView
    private lateinit var tvDuration: TextView
    private lateinit var image: ImageView
    private lateinit var imgBackground: ImageView
    private lateinit var btnDelete: View
    private lateinit var filmHeler: FilmHelper
    private lateinit var film: Film


    companion object{
        const val INTENT_PARCELABLE = "OBJECT_INTERN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_mylist)

        film = intent.getParcelableExtra<Film>(INTENT_PARCELABLE)!!
        filmHeler = FilmHelper(this)
        filmHeler = FilmHelper.getInstance(applicationContext)
        supportActionBar?.hide()



        tvJudul = findViewById(R.id.detail_movie_title)
        tvRating = findViewById(R.id.rate)
        tvType = findViewById(R.id.tv_detail_type)
        tvSinopsis = findViewById(R.id.detail_movie_desc)
        tvCast = findViewById(R.id.tv_detail_cast)
        tvReleaseDate = findViewById(R.id.tv_release_date)
        tvDuration = findViewById(R.id.tv_duration)
        image = findViewById(R.id.detail_movie_img)
        imgBackground = findViewById(R.id.detail_movie_cover)
        btnDelete = findViewById(R.id.fab_delete)
        btnDelete.setOnClickListener{
            deleteList()
        }

        if(film != null){
            Log.e("id ", "${film.ID}")
            tvJudul.text = film.judul
            tvRating.text = film.rating.toString()
            tvType.text = film.filmType
            tvSinopsis.text = film.sinopsis
            tvCast.text = film.actor
            tvReleaseDate.text = film.tanggalTerbit
            tvDuration.text = film.duration.toString()
            Picasso.get().load(film.image).into(image)
            Picasso.get().load(film.imgBackground).into(imgBackground)
        }

    }

    private fun deleteList(){
        filmHeler.open()
        val status = filmHeler.deleteById(film.ID.toString()).toLong()
        if(status > 0){
            Toast.makeText(this@DetailMyListActivity, "Film/Drama Removed From List", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }else{
            Toast.makeText(this@DetailMyListActivity, "Film/Drama Failed Removed From List", Toast.LENGTH_SHORT).show()
        }
        filmHeler.close()
    }
}