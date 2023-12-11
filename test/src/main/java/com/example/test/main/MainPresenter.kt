package com.example.test.main

import com.example.test.R
import com.example.test.home.HomeFragment
import com.example.test.setting.FragmentSetting
import com.example.test.setting_2.SettingsFragment2

class MainPresenter (private val view: MainContract.View) : MainContract.Presenter {

    override fun onBottomNavigationItemSelected(itemId: Int) {
        when (itemId) {
            R.id.action_home -> view.switchFragment(HomeFragment())
            R.id.action_settings -> view.switchFragment(FragmentSetting())
            R.id.action_settings_2 -> view.switchFragment(SettingsFragment2())
        }
    }
}