package com.example.movies.data.repositpry

import com.example.movies.data.model.Movie
import com.example.movies.data.repositpry.source.MovieDataSource
import com.example.movies.data.repositpry.source.remote.OnResultListener

class MovieRepository private constructor(
    private val remote: MovieDataSource.Remote,
    private val local: MovieDataSource.Local
) : MovieDataSource.Local, MovieDataSource.Remote {
    override fun getMoviesLocal(listener: OnResultListener<MutableList<Movie>>) {
        //TODO("Not yet implemented")
    }

    override fun getMovies(listener: OnResultListener<MutableList<Movie>>) {
        remote.getMovies(listener)
    }

    companion object {
        private var instance: MovieRepository? = null

        fun getInstance(remote: MovieDataSource.Remote, local: MovieDataSource.Local) =
            synchronized(this) {
                instance ?: MovieRepository(remote, local).also { instance = it }
            }
    }
}