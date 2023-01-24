package com.configcentre.projecta.configupdatereceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.configcentre.projecta.util.EXTRA_CONFIGURATION
import com.configcentre.projecta.util.SharedPreferenceManager

class ConfigUpdateBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.extras?.getString(EXTRA_CONFIGURATION)?.run {
            context?.let {
                SharedPreferenceManager(context = it)
                    .putString(prefKey = EXTRA_CONFIGURATION, prefValue = this)
            }
        }
    }
}
