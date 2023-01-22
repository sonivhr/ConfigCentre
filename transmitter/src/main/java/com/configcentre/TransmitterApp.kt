package com.configcentre

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.configcentre.transmitter.ConfigurationUpdateWorker
import java.util.concurrent.TimeUnit

class TransmitterApp : Application() {
    override fun onCreate() {
        super.onCreate()
        scheduleConfigUpdates()
    }

    private fun scheduleConfigUpdates() {
        val configurationUpdateConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED).build()
        val periodicWorkRequest = PeriodicWorkRequestBuilder<ConfigurationUpdateWorker>(
            repeatInterval = INTERVAL_PERIODIC_WORK_REQUEST,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        )
            .setConstraints(configurationUpdateConstraints)
            .build()

        WorkManager.getInstance(this).enqueue(periodicWorkRequest)
    }

    private companion object {
        private const val INTERVAL_PERIODIC_WORK_REQUEST: Long = 15
    }
}
