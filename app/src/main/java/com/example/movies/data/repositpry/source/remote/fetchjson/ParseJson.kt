package com.example.movies.data.repositpry.source.remote.fetchjson


import com.example.movies.data.model.Movie
import com.example.movies.data.model.MovieEntry
import org.json.JSONObject

class ParseJson {
    /**
     * phân tích một đối tượng JSON -> Movie
     */
    fun movieParseJson(jsonObject: JSONObject) = Movie().apply {
        jsonObject.let {
            vote = it.getInt(MovieEntry.VOTE)
            title = it.getString(MovieEntry.TITLE)
            urlImage = it.getString(MovieEntry.URL_IMAGE)
            originalTitle = it.getString(MovieEntry.ORIGINAL_TITLE)
            voteCount = it.getInt(MovieEntry.VOTE_COUNT)
            backDropImage = it.getString(MovieEntry.BACKDROP_IMAGE)
            overView = it.getString(MovieEntry.OVERVIEW)
        }
    }
}
