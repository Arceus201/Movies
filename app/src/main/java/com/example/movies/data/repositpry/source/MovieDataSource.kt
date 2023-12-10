package com.example.movies.data.repositpry.source

import com.example.movies.data.model.Movie
import com.example.movies.data.repositpry.source.remote.OnResultListener

interface MovieDataSource {
    interface Local {
        fun getMoviesLocal(listener: OnResultListener<MutableList<Movie>>)
    }

    /**
     * Remote
     */
    interface Remote {
        fun getMovies(listener: OnResultListener<MutableList<Movie>>)
    }
}