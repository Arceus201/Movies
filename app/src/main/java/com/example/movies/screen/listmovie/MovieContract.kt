package com.example.movies.screen.listmovie

import com.example.movies.data.model.Movie
import com.example.movies.utlis.base.BasePresenter
import java.lang.Exception

class MovieContract {
    /**
     * View
     */
    interface View {
        fun onGetMoviesSuccess(movies: MutableList<Movie>)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun getMovies()
    }
}