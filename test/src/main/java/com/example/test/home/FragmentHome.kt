package com.example.test.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.test.R

// HomeFragment.kt
// HomeFragment.kt
// HomeFragment.kt
class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var presenter: HomeContract.Presenter
    private lateinit var webViewGoogle: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        webViewGoogle = view.findViewById(R.id.webViewGoogle)

        // Set up onBackPressed callback
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                presenter.onBackPressed()
            }
        })

        presenter = HomePresenter(this)
        presenter.onViewCreated(savedInstanceState)

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroyView()
    }

    override fun loadUrl(url: String) {
        webViewGoogle.loadUrl(url)
    }

    override fun configureWebViewSettings() {
        val settings = webViewGoogle.settings

        // Enable JavaScript
        settings.javaScriptEnabled = true

        // Enable zoom control
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = false

        // Enable links outside of the app to open in the default browser
        webViewGoogle.webViewClient = WebViewClient()
    }

    override fun canWebViewGoBack(): Boolean {
        return webViewGoogle.canGoBack()
    }

    override fun goBackInWebView() {
        webViewGoogle.goBack()
    }

    override fun getUrl(): String? {
        return webViewGoogle.url
    }
}
