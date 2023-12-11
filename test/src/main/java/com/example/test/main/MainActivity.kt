package com.example.test.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.test.R

import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() , MainContract.View{

    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        presenter = MainPresenter(this)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            presenter.onBottomNavigationItemSelected(item.itemId)
            return@setOnNavigationItemSelectedListener true
        }

        // Mặc định hiển thị Fragment Home khi mở ứng dụng
        presenter.onBottomNavigationItemSelected(R.id.action_home)
    }

    override fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}