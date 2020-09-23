package com.yomna.mymoviesapp.com.yomna.mymoviesapp

import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("results") val movies : MutableList<MovieModel>,
    @SerializedName("page") val currentPage : Int,
    @SerializedName("total_pages") val totalPages : Int
)