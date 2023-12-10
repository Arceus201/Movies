package com.example.movies.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.R
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.databinding.FragmentMoviesBinding
import com.example.movies.screen.listmovie.MovieFragment
import com.example.movies.utlis.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){
    override fun initView() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(FragmentMoviesBinding::javaClass.name)
            .replace(R.id.layoutContainer,MovieFragment.newInstance())
            .commit()
    }

    override fun initData() {

    }

    override fun handleEvent() {

    }

}