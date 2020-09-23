package com.yomna.mymoviesapp.com.yomna.mymoviesapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey : String = "79465a9643e424e21d896775955ea2f1",
        @Query("page") pageNumber: Int = 1) : Call<MoviesResponse>

}