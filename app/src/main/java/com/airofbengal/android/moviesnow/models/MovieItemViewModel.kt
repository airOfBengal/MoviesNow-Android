package com.airofbengal.android.moviesnow.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.airofbengal.android.moviesnow.repositories.MoviesFetcher

class MovieItemViewModel: ViewModel() {
    val movieItemLiveData: LiveData<List<MovieItem>>

    init {
        movieItemLiveData = MoviesFetcher().fetchContents()
    }
}