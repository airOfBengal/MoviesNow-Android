package com.airofbengal.android.moviesnow.fragments

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airofbengal.android.moviesnow.R
import com.airofbengal.android.moviesnow.models.MovieItem
import com.airofbengal.android.moviesnow.models.MovieItemViewModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "MovieListFragment"

class MovieListFragment: Fragment() {

    private lateinit var movieItemViewModel: MovieItemViewModel
    private lateinit var moviesRecyclerView: RecyclerView
    private var adapter: MovieAdapter? = MovieAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieItemViewModel = ViewModelProviders.of(this).get(MovieItemViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_list_recycler_view, container, false)
        moviesRecyclerView = view.findViewById(R.id.movies_recycler_view) as RecyclerView
        moviesRecyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieItemViewModel.movieItemLiveData.observe(
            viewLifecycleOwner,
            Observer { movieItems ->
                moviesRecyclerView.adapter = MovieAdapter(movieItems)
            }
        )
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private inner class MovieHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var movieItem: MovieItem

        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val photoImageView: ImageView = itemView.findViewById(R.id.photo)
        val dateTextView: TextView = itemView.findViewById(R.id.date)
        val voteCountTextView: TextView = itemView.findViewById(R.id.vote_count)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movieItem: MovieItem) {
            this.movieItem = movieItem
            titleTextView.text = this.movieItem.title
            Picasso.get()
                    .load("https://image.tmdb.org/t/p/w342"+this.movieItem.posterPath)
                    .into(photoImageView)

            dateTextView.text = this.movieItem.date.toString()
            voteCountTextView.text = this.movieItem.voteCount.toString()
        }

        override fun onClick(v: View?) {

        }

    }

    private inner class MovieAdapter(private val movieItems: List<MovieItem>):
        RecyclerView.Adapter<MovieHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
            val view = layoutInflater.inflate(R.layout.list_item_movie, parent, false)
            return MovieHolder(view)
        }

        override fun onBindViewHolder(holder: MovieHolder, position: Int) {
            val movieItem = movieItems[position]
            holder.bind(movieItem)
        }

        override fun getItemCount(): Int = movieItems.size

    }
}