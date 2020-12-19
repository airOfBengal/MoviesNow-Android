package com.airofbengal.android.moviesnow.apis

import com.airofbengal.android.moviesnow.models.MovieItem
import com.google.gson.annotations.SerializedName

class MovieItemsResponse {
    var page: Int = 0
    @SerializedName("results")
    lateinit var movieItems: List<MovieItem>
}