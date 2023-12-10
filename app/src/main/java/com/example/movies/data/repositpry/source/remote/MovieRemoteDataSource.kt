package com.example.movies.data.repositpry.source.remote

import com.example.movies.data.model.Movie
import com.example.movies.data.model.MovieEntry
import com.example.movies.data.repositpry.source.MovieDataSource
import com.example.movies.data.repositpry.source.remote.fetchjson.GetJsonFromUrl
import com.example.movies.utlis.Constant

class MovieRemoteDataSource : MovieDataSource.Remote {
    override fun getMovies(listener: OnResultListener<MutableList<Movie>>) {
        GetJsonFromUrl(
            urlString = Constant.BASE_URL + Constant.BASE_PAGE,
            keyEntity = MovieEntry.MOVIES,
            listener = listener
        )
    }

    companion object {
        private var instance: MovieRemoteDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: MovieRemoteDataSource().also { instance = it }
        }
    }
}