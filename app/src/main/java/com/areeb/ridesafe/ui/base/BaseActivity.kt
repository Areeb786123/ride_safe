package com.areeb.ridesafe.ui.base

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat

open class BaseActivity : ComponentActivity() {

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    fun checkPhonePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.MODIFY_PHONE_STATE,
            ),
            123,
        )
    }
}

//    @SuppressLint("UnspecifiedRegisterReceiverFlag")
//     val requestPermissionLauncher: ActivityResultLauncher<String> =
//        registerForActivityResult(
//            ActivityResultContracts.RequestPermission(),
//        ) { isGranted: Boolean ->
//            if (isGranted) {
//                registerReceiver(incomingCallReceiver, filter)
//            } else {
//                AlertDialog.Builder(this)
//                    .setTitle("permission denied")
//                    .setMessage("please allow it ")
//                    .setNegativeButton("ok") { dialog, _ ->
//                        dialog.cancel()
//                    }
//                    .show()
//            }
//        }
