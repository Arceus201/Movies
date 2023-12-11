package com.example.test.setting

import com.example.test.home.HomeFragment

class SettingPresenter(private val view: SettingContract.View) : SettingContract.Presenter {

    override fun onBackButtonClicked() {
        // Handle back button click
        view.switchFragment(HomeFragment())
    }
}