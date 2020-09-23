package com.yomna.mymoviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val title = intent.getStringExtra("title")
        movieTitle2.text = title


        val poster = intent.getStringExtra("poster")

        val posterPath = Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342${poster}")
            .transform(CenterCrop())
            .into(moviePoster2)


        val releaseDate = intent.getStringExtra("release_date")
        releaseDate2.text = releaseDate


        val rating = intent.getFloatExtra("vote_average",0.0F)
        averageVote2.text = rating.toString()


        val originalLanguage = intent.getStringExtra("original_language")
        language2.text = originalLanguage


        val overview = intent.getStringExtra("overview")
        overView2.text = overview

    }
}