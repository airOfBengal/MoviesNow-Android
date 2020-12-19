package com.airofbengal.android.moviesnow.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieItem (
    var id: String = "",
    var title:String = "",
    @SerializedName("release_date") var date: Date = Date(),
    @SerializedName("vote_count") var voteCount: Int = 0,
    @SerializedName("poster_path") var posterPath: String = ""
)