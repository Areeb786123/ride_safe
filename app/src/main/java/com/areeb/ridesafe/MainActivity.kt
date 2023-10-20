package com.areeb.ridesafe

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.areeb.ridesafe.ui.base.BaseActivity
import com.areeb.ridesafe.ui.rideSafeManager.CallManager
import com.areeb.ridesafe.ui.theme.Ride_safeTheme

class MainActivity : BaseActivity() {
    private val incomingCallReceiver = CallManager(this)
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123) {
            registerReceiver(incomingCallReceiver, filter)
        } else {
            Log.e("main", "some error occut")
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
