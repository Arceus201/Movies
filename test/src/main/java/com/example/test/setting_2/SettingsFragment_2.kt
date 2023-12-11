package com.example.test.setting_2

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.test.R

// Fragment (View)
class SettingsFragment2 : Fragment(), SettingsContract2.View {

    private lateinit var notificationSwitch: Switch
    private lateinit var presenter: SettingsContract2.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting2, container, false)

        notificationSwitch = view.findViewById(R.id.notificationSwitch)

        // Initialize presenter
        presenter = SettingsPresenter2(this)
        presenter.onViewCreated()

        // Set up listener for Switch
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            presenter.onNotificationSwitchChanged(isChecked)
        }

        // Other initialization code

        return view
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        presenter.onRequestPermissionsResult(requestCode, grantResults)
    }

    override fun showNotificationPermissionDialog() {
        // Show a dialog to request notification permission
        requestNotificationPermission()
    }

    override fun showPermissionDeniedMessage() {
        // Show a message to the user that the permission is denied
    }

    private fun requestNotificationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.RECEIVE_BOOT_COMPLETED),
            presenter.getNotificationPermissionCode()
        )
    }
}