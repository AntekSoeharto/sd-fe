package com.example.sugardaddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.sugardaddy.Entity.Film
import com.example.sugardaddy.Entity.News
import com.squareup.picasso.Picasso

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvAuthor: TextView
    private lateinit var tvRank: TextView
    private lateinit var tvCountry: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvPublishedDate: TextView
    private lateinit var tvLink: TextView
    private lateinit var image: ImageView

    companion object{
        const val INTENT_PARCELABLE = "OBJECT_INTERN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val news = intent.getParcelableExtra<News>(DetailActivity.INTENT_PARCELABLE)

        tvTitle = findViewById(R.id.detail_news_title)
        tvAuthor = findViewById(R.id.author)
        tvRank = findViewById(R.id.tv_detail_rank)
        tvCountry = findViewById(R.id.tv_detail_country)
        tvDescription = findViewById(R.id.detail_news_desc)
        tvPublishedDate = findViewById(R.id.tv_detail_published_date)
        tvLink = findViewById(R.id.tv_detail_link)
        image = findViewById(R.id.iv_news)
        supportActionBar?.hide()

        if(news != null){
            tvTitle.text = news.title
            tvAuthor.text = news.author
            tvRank.text = news.rank.toString()
            tvCountry.text = news.country
            tvDescription.text = news.description
            tvPublishedDate.text = news.publishedDate
            tvLink.text = news.link
            Picasso.get().load(news.image).fit().centerCrop().into(image)
        }

    }
}