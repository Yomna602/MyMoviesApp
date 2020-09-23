package com.yomna.mymoviesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yomna.mymoviesapp.com.yomna.mymoviesapp.MoviesClient
import kotlinx.android.synthetic.main.activity_main.*

import com.yomna.mymoviesapp.com.yomna.mymoviesapp.MovieModel

class MainActivity : AppCompatActivity() {
    var currentPageNumber = 1
    lateinit var moviesAdapter: MoviesAdaptor
    lateinit var llm: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


           // item_movie_poster.setOnClickListener {
           //    val intent = Intent( this, DetailsActivity ::class.java)
           //     startActivity(intent)
           // }


        moviesAdapter = MoviesAdaptor(mutableListOf())
        llm = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,
            false)
        rv_movies.adapter = moviesAdapter
        rv_movies.layoutManager = llm
        getPopularMovies()
        // This is in development branch
    }

    fun getPopularMovies(){
        Log.d("Popular Movies",
            "here" )
        MoviesClient.fetchPopularMovies(
            currentPageNumber,
            ::onPopularMoviesFetched,
            ::onError)
    }
    private fun onError() {
        Toast
            .makeText(this, "Failed to fetch movies", Toast.LENGTH_SHORT)
            .show()
    }

    private fun onPopularMoviesFetched(list: MutableList<MovieModel>) {
        moviesAdapter.appendMovies(list)
        attachOnScrollListener()
    }
    fun attachOnScrollListener(){
        rv_movies.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItems = llm.itemCount
                val visibleItemsCount = llm.childCount
                val firstVisibleItem = llm.findLastVisibleItemPosition()

                if(firstVisibleItem + visibleItemsCount >= totalItems/2){
                    rv_movies.removeOnScrollListener(this)
                    currentPageNumber++
                    getPopularMovies()
                }
            }
        })
    }

    fun onMovieCardClicked(view: View) {

        val position = llm.getPosition(view)
        val movie = moviesAdapter.moviesList!![position]

        val intent = Intent(this,DetailsActivity::class.java)
        val extras = Bundle()

        extras.putString("title", movie.title)

        extras.putString("poster", movie.backdropPath)

        extras.putString("release_date", movie.releaseDate)

        extras.putFloat("vote_average", movie.rating)

        extras.putString("original_language", movie.originalLanguage)

        extras.putString("overview", movie.overview)

        intent.putExtras(extras)
        startActivity(intent)
    }
}

