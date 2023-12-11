package com.example.test.home

import android.os.Bundle

// HomePresenter.kt
class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter {

    private var webViewUrl: String? = null

    override fun onViewCreated(savedInstanceState: Bundle?) {
        // Restore WebView state if it exists
        webViewUrl = savedInstanceState?.getString(KEY_WEB_VIEW_URL)

        webViewUrl?.let { url ->
            view.loadUrl(url)
        } ?: run {
            // If there's no saved state, load the default URL
            view.configureWebViewSettings()
            view.loadUrl("https://www.google.com/")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_WEB_VIEW_URL, webViewUrl)
    }

    override fun onDestroyView() {
        // Save WebView state to Presenter
        webViewUrl = view.getUrl()
    }

    override fun onBackPressed() {
        if (view.canWebViewGoBack()) {
            view.goBackInWebView()
        } else {
            // Handle other back press actions or exit the app
        }
    }

    companion object {
        private const val KEY_WEB_VIEW_URL = "web_view_url"
    }
}
