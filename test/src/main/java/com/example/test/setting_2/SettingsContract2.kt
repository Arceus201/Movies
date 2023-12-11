package com.example.test.setting_2

import android.content.Context
import androidx.fragment.app.FragmentActivity


interface SettingsContract2 {
    interface View {
        fun showNotificationPermissionDialog()
        fun showPermissionDeniedMessage()
    }

    interface Presenter {
        fun onViewCreated()
        fun onNotificationSwitchChanged(isChecked: Boolean)
        fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray)
        fun getNotificationPermissionCode(): Int
    }
}