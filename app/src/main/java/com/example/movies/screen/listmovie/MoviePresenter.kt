package com.example.movies.screen.listmovie

import android.content.Context
import com.example.movies.data.model.Movie
import com.example.movies.data.repositpry.MovieRepository
import com.example.movies.data.repositpry.source.remote.OnResultListener
import java.lang.Exception

class MoviePresenter internal constructor(private val movieRepository: MovieRepository?) :
    MovieContract.Presenter {

    private var mView: MovieContract.View? = null

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun setView(view: MovieContract.View?) {
        this.mView = view
    }

    override fun getMovies() {
        movieRepository?.getMovies(object : OnResultListener<MutableList<Movie>> {
            override fun onSuccess(data: MutableList<Movie>) {
                mView?.onGetMoviesSuccess(data)
            }

            override fun onError(exception: Exception?) {
                mView?.onError(exception)
            }
        })
    }
}