package com.example.movies.data.repositpry.source.local

import com.example.movies.data.model.Movie
import com.example.movies.data.repositpry.source.MovieDataSource
import com.example.movies.data.repositpry.source.remote.OnResultListener

class MovieLocalDataSource: MovieDataSource.Local  {
    override fun getMoviesLocal(listener: OnResultListener<MutableList<Movie>>) {
        //TODO("Not yet implemented")
    }

    companion object {
        private var instance: MovieLocalDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: MovieLocalDataSource().also { instance = it }
        }
    }
}