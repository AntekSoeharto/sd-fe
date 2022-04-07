package com.example.sugardaddy

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.sugardaddy.BottomNavigationFragment.DramaFragment
import com.example.sugardaddy.BottomNavigationFragment.FilmFragment
import com.example.sugardaddy.Entity.Film
import com.squareup.picasso.Picasso
import java.io.File

class DetailActivity : AppCompatActivity() {

    private lateinit var tvJudul: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvType: TextView
    private lateinit var tvSinopsis: TextView
    private lateinit var tvCast: TextView
    private lateinit var tvReleaseDate: TextView
    private lateinit var tvDuration: TextView
    private lateinit var image: ImageView
    private lateinit var imgBackground: ImageView

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

        if (detailDrama != null){
            tvJudul.text = detailDrama.judul
            tvRating.text = detailDrama.rating.toString()
            tvType.text = detailDrama.filmType
            tvSinopsis.text = detailDrama.sinopsis
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
            tvCast.text = detailFilm.actor
            tvReleaseDate.text = detailFilm.tanggalTerbit
            tvDuration.text = detailFilm.duration.toString()
            Picasso.get().load(detailFilm.image).into(image)
            Picasso.get().load(detailFilm.imgBackground).into(imgBackground)
        }

    }
}