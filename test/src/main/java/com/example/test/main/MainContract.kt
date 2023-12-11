package com.example.test.main

import androidx.fragment.app.Fragment

class MainContract {
    interface View {
        fun switchFragment(fragment: Fragment)
    }

    interface Presenter {
        fun onBottomNavigationItemSelected(itemId: Int)
    }
}