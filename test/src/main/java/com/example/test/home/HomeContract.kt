package com.example.test.home

import android.os.Bundle

// HomeContract.kt
interface HomeContract {
    interface View {
        fun loadUrl(url: String)
        fun configureWebViewSettings()
        fun canWebViewGoBack(): Boolean
        fun goBackInWebView()
        fun getUrl(): String?
    }

    interface Presenter {
        fun onViewCreated(savedInstanceState: Bundle?)
        fun onSaveInstanceState(outState: Bundle)
        fun onDestroyView()
        fun onBackPressed()
    }
}
