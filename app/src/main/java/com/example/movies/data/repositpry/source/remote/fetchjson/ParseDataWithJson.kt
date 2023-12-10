package com.example.movies.data.repositpry.source.remote.fetchjson

import com.example.movies.data.model.MovieEntry
import com.example.movies.utlis.ext.notNull
import org.json.JSONException
import org.json.JSONObject

/**
 * Phân tích dữ liệu từ JSONObject thành một danh sách các đối tượng
 * dựa trên khóa cung cấp
 */
class ParseDataWithJson {
    /**
     * Lấy mảng Json trên keyEntity
     * chuyển đổi -> Movie
     * add data
     */
    fun parseJsonToData(jsonObject: JSONObject?, keyEntity: String): Any {
        val data = mutableListOf<Any>()
        try {
            val jsonArray = jsonObject?.getJSONArray(keyEntity)
            for (i in 0 until (jsonArray?.length() ?: 0)) {
                val item = parseJsonToObject(jsonArray?.getJSONObject(i), keyEntity)
                item.notNull {
                    data.add(it)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return data
    }

    /**
     * Nhận vào 1 JSONObject và KeyEntity
     * nếu KeyEntity là movies
     * thì sử dụng Class ParseJon chuyển đổi -> Movie
     */
    private fun parseJsonToObject(jsonObject: JSONObject?, keyEntity: String): Any? {
        try {
            jsonObject?.notNull {
                return when(keyEntity) {
                    MovieEntry.MOVIES -> ParseJson().movieParseJson(it)
                    else -> null
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }
}
