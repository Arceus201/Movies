package com.example.test.setting_2




import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.test.setting.SettingContract

// Presenter
class SettingsPresenter2(private val view: SettingsContract2.View) : SettingsContract2.Presenter {

    private val notificationPermissionCode = 123


    override fun getNotificationPermissionCode(): Int {
        return notificationPermissionCode
    }
    override fun onViewCreated() {
        // Perform any initialization logic here
    }

    override fun onNotificationSwitchChanged(isChecked: Boolean) {
        if (isChecked) {
            if (checkNotificationPermission()) {
                // Permission already granted, handle accordingly
                grantNotificationPermission()
            } else {
                // Request permission if not granted
                view.showNotificationPermissionDialog()
            }
        } else {
            // Switch is OFF
            // Implement logic to revoke notification permission if needed
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (requestCode == notificationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, handle accordingly
                grantNotificationPermission()
            } else {
                // Permission denied, handle accordingly (e.g., show a message to the user)
                view.showPermissionDeniedMessage()
            }
        }
    }

    private fun checkNotificationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            // Use the view's context to avoid memory leaks
            (view as Fragment).requireContext(),
            Manifest.permission.RECEIVE_BOOT_COMPLETED
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun grantNotificationPermission() {
        // Implement your logic to handle the case when permission is granted
    }
}