package com.airofbengal.android.moviesnow.apis

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface MoviesApi {
    @GET("discover/movie/?api_key=1a97f3b8d5deee1d649c0025f3acf75c"+
    "&primary_release_year=2020"+
    "&sort_by=vote_average.desc")
    fun fetchMovies(): Call<MovieItemsResponse>

    @GET
    fun fetchUrlBytes(@Url url: String): Call<ResponseBody>
}