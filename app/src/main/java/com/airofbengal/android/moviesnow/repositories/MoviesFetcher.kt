package com.airofbengal.android.moviesnow.repositories

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.airofbengal.android.moviesnow.apis.MovieItemsResponse
import com.airofbengal.android.moviesnow.apis.MoviesApi
import com.airofbengal.android.moviesnow.models.MovieItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MoviesFetcher"

class MoviesFetcher {
    private val moviesApi: MoviesApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        moviesApi = retrofit.create(MoviesApi::class.java)
    }

    fun fetchContents(): LiveData<List<MovieItem>> {
        val responseLiveData: MutableLiveData<List<MovieItem>> = MutableLiveData()
        val moviesRequest: Call<MovieItemsResponse> = moviesApi.fetchMovies()

        moviesRequest.enqueue(object : Callback<MovieItemsResponse> {
            override fun onFailure(call: Call<MovieItemsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: Failed to fetch photos", t)
            }

            override fun onResponse(call: Call<MovieItemsResponse>, response: Response<MovieItemsResponse>) {
                val moviesResponse: MovieItemsResponse? = response.body()
//                val movieItemsResponse: MovieItemsResponse? = moviesResponse?.movies
                var movieItems: List<MovieItem> = moviesResponse?.movieItems ?: mutableListOf()

                movieItems = movieItems.filterNot {
                    it.posterPath.isBlank()
                }

                responseLiveData.value = movieItems
            }
        })

        return responseLiveData
    }

    @WorkerThread
    fun fetchPhoto(url: String): Bitmap? {
        val response: Response<ResponseBody> = moviesApi.fetchUrlBytes(url).execute()
        val bitmap = response.body()?.byteStream()?.use(BitmapFactory::decodeStream)
        return bitmap
    }
}