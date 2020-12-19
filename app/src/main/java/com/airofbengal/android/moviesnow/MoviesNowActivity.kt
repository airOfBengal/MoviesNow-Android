package com.airofbengal.android.moviesnow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airofbengal.android.moviesnow.fragments.MovieListFragment

class MoviesNowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_now)

        val isMoviesFragmentEmpty = savedInstanceState == null
        if (isMoviesFragmentEmpty){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.movies_fragment_container, MovieListFragment.newInstance())
                .commit()
        }
    }
}