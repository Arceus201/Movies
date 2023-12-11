package com.example.test.setting

import androidx.fragment.app.Fragment

class SettingContract {
    interface View {
        fun switchFragment(fragment: Fragment)
    }

    interface Presenter {
        fun onBackButtonClicked()
    }
}