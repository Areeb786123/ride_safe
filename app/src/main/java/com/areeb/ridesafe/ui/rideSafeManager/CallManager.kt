package com.areeb.ridesafe.ui.rideSafeManager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast

class CallManager constructor(val context: Context) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        var state = intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            Toast.makeText(context, "Ringing State Number is -  ", Toast.LENGTH_SHORT).show()
            Log.e("testx", "incoming call")
        }
        if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))) {
            Toast.makeText(context, "Received State", Toast.LENGTH_SHORT).show()
        }
        if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            Toast.makeText(context, "Idle State", Toast.LENGTH_SHORT).show()
        }
    }
}
