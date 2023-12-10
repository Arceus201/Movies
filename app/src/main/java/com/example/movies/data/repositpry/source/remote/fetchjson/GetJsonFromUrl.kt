package com.example.movies.data.repositpry.source.remote.fetchjson

import android.os.Handler
import android.os.Looper
import com.example.movies.data.repositpry.source.remote.OnResultListener
import com.example.movies.utlis.Constant
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * gọi API từ một URL và
 * phân tích dữ liệu JSON nhận được thành danh sách các đối tượng dựa vào keyEntity
 */


/**
 * Nhận một URL,
 * keyEntity ,
 * một listener để lắng nghe kết quả trả về.
 */
class GetJsonFromUrl<T> constructor(
    private val urlString: String,
    private val keyEntity: String,
    private val listener: OnResultListener<T>
) {
    /**
     * Khai báo các thuộc tính
     */
    private val mExecutor: Executor = Executors.newSingleThreadExecutor()
    private val mHandler = Handler(Looper.getMainLooper())
    private var exception: Exception? = null
    private var data: T? = null

    /**
     * khởi tạo
     */
    init {
        callAPI()
    }

    /**
     * gọi API bất đồng bộ bằng cách sử dụng Executor,
     * sau đó phân tích dữ liệu JSON bằng lớp ParseDataWithJson
     */
    private fun callAPI() {
        mExecutor.execute {
            /**
             * Gọi hàm getJsonFromUrl để lấy dữ liệu JSON từ URL
             */
            val responseJson =
                getJsonFromUrl(urlString + Constant.BASE_API_KEY + Constant.BASE_LANGUAGE)
            /**
             *  ParseDataWithJson để phân tích dữ liệu JSON thành danh sách các đối tượng dựa vào keyEntity
             */
            data = ParseDataWithJson().parseJsonToData(JSONObject(responseJson), keyEntity) as? T
            /**
             * Gửi kết quả trả về bằng cách đưa vào MainThread thông qua Handler
             */
            mHandler.post {
                try {
                    /**
                     * Nếu dữ liệu không null, gọi phương thức onSuccess trên listener
                     */
                    data?.let { listener.onSuccess(it) }
                } catch (e: Exception) {
                    /**
                     * Nếu xảy ra lỗi, gọi phương thức onError trên listener
                     */
                    listener.onError(exception)
                }
            }
        }
    }

    /**
     * lấy dữ liệu JSON từ URL
     */
    private fun getJsonFromUrl(urlString: String): String {
        val url = URL(urlString)
        val httpURLConnection = url.openConnection() as? HttpURLConnection
        httpURLConnection?.run {
            connectTimeout = TIME_OUT
            readTimeout = TIME_OUT
            requestMethod = METHOD_GET
            doOutput = true
            connect()
        }

        val bufferedReader = BufferedReader(InputStreamReader(url.openStream()))
        val stringBuilder = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        bufferedReader.close()
        httpURLConnection?.disconnect()
        return stringBuilder.toString()
    }

    companion object {
        private const val TIME_OUT = 15000
        private const val METHOD_GET = "GET"
    }
}


