package com.example.movies.screen.listmovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.data.model.Movie
import com.example.movies.databinding.ItemLayoutMovieBinding
import com.example.movies.utlis.ext.loadImageCircleWithUrl
import com.example.movies.utlis.ext.notNull
import com.example.movies.utlis.listener.OnItemRecyclerViewClickListener

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder?>() {

    private val movies = mutableListOf<Movie>()
    private var onItemClickListener: OnItemRecyclerViewClickListener<Movie>? = null

    fun setData(movies: MutableList<Movie>?) {
        movies.notNull {
            this.movies.clear()
            this.movies.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun registerItemRecyclerViewClickListener(
        onItemRecyclerViewClickListener: OnItemRecyclerViewClickListener<Movie>?
    ) {
        onItemClickListener = onItemRecyclerViewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding = ItemLayoutMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(viewBinding, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies.get(position)
        holder.viewBinding.apply {
            movie.let {
                textViewTitle?.text = it.title
                textViewRatting?.text = it.vote.toString()
                textViewContent?.text = it.originalTitle
                imageMovie?.loadImageCircleWithUrl(it.urlImage)

                root.setOnClickListener {
                    onItemClickListener?.onItemClick(movie)
                }
            }
        }
    }

    inner class ViewHolder(
        var viewBinding: ItemLayoutMovieBinding,
        listener: OnItemRecyclerViewClickListener<Movie>?
    ) : RecyclerView.ViewHolder(viewBinding.root) {

    }
}