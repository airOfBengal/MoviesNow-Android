package com.airofbengal.android.moviesnow.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airofbengal.android.moviesnow.R
import com.airofbengal.android.moviesnow.apis.MoviesApi
import com.airofbengal.android.moviesnow.models.MovieItem
import com.airofbengal.android.moviesnow.repositories.MoviesFetcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "MovieListFragment"

class MovieListFragment: Fragment() {
    private lateinit var moviesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val moviesLiveData: LiveData<List<MovieItem>>  = MoviesFetcher().fetchContents()
        moviesLiveData.observe(
            this,
            Observer { movieItems ->
                Log.d(TAG, "onCreate: Response received: $movieItems")
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_list_recycler_view, container, false)
        moviesRecyclerView = view.findViewById(R.id.movies_recycler_view)
        moviesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return view
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }
}