package com.areeb.ridesafe

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.areeb.ridesafe.ui.rideSafeManager.CallManager
import com.areeb.ridesafe.ui.theme.Ride_safeTheme

class MainActivity : ComponentActivity() {
    val incomingCallReceiver = CallManager(this)
    val filter = IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED)

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPhonePermission()
        setContent {
            Ride_safeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Greeting("hello Ride safer user  😍")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(incomingCallReceiver)
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) { isGranted: Boolean ->
            if (isGranted) {
                registerReceiver(incomingCallReceiver, filter)
            } else {
                AlertDialog.Builder(this)
                    .setTitle("permission denied")
                    .setMessage("please allow it ")
                    .setNegativeButton("ok") { dialog, _ ->
                        dialog.cancel()
                    }
                    .show()
            }
        }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    private fun checkPhonePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_PHONE_STATE,
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            registerReceiver(incomingCallReceiver, filter)
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.READ_PHONE_STATE)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ride_safeTheme {
        Greeting("Android")
    }
}
