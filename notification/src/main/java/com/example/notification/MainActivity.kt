package com.example.notification

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val requestPermissionButton: Button = findViewById(R.id.requestPermissionButton)
        requestPermissionButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Kiểm tra xem thiết bị có chạy Android 13 (API level 33) trở lên không
                requestNotificationPermission()
            } else {
                // Thiết bị chạy dưới Android 13, không cần yêu cầu quyền
                showToast("Not supported on devices running below Android 13")
            }
        }
    }

    private fun requestNotificationPermission() {
        // Kiểm tra xem quyền đã được cấp hay chưa
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Nếu quyền chưa được cấp, yêu cầu quyền từ người dùng
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS
            )
        } else {
            // Nếu quyền đã được cấp, hiển thị Toast thông báo
            showToast("Notification permission already granted")
        }
    }

    // Xử lý kết quả từ yêu cầu quyền
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS) {
            // Kiểm tra xem người dùng đã chấp nhận quyền hay không
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Người dùng đã chấp nhận quyền
                showToast("Notification permission granted")
            } else {
                // Người dùng đã từ chối quyền
                showToast("Notification permission denied")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}