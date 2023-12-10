package com.example.movies.screen.listmovie

import android.view.View
import android.widget.Toast
import com.example.movies.R
import com.example.movies.data.model.Movie
import com.example.movies.data.repositpry.MovieRepository
import com.example.movies.data.repositpry.source.local.MovieLocalDataSource
import com.example.movies.data.repositpry.source.remote.MovieRemoteDataSource
import com.example.movies.databinding.FragmentMoviesBinding
import com.example.movies.screen.detail.DetailFragment
import com.example.movies.screen.listmovie.adapter.MovieAdapter
import com.example.movies.utlis.base.BaseFragment
import com.example.movies.utlis.ext.addFragment
import com.example.movies.utlis.listener.OnItemRecyclerViewClickListener
import java.lang.Exception

class MovieFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate),
    MovieContract.View,
    OnItemRecyclerViewClickListener<Movie> {
    private lateinit var mMoviesPresenter: MoviePresenter
    private val mMovieAdapter: MovieAdapter by lazy { MovieAdapter() }

    override fun initView(view: View) {
        binding.recyclerViewMovie.adapter = mMovieAdapter
        mMovieAdapter.registerItemRecyclerViewClickListener(this)
    }

    override fun initData() {
        mMoviesPresenter = MoviePresenter(
            MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getInstance()
            )
        )
        mMoviesPresenter.setView(this)
        mMoviesPresenter.getMovies()
    }

    override fun handleEvent() {

    }

    override fun onGetMoviesSuccess(movies: MutableList<Movie>) {
        mMovieAdapter.setData(movies)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(item: Movie?) {
        addFragment(R.id.layoutContainer, DetailFragment.newInstance(item), true)
    }

    companion object {
        fun newInstance() = MovieFragment()
    }
}