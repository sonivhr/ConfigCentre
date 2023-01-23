package com.configcentre.transmitter

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.configcentre.model.Configuration
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ConfigurationUpdateWorker(
    private val context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    private lateinit var databaseReference: DatabaseReference

    override fun doWork(): Result {
        databaseReference = Firebase.database.reference
        updateConfigurations()
        return Result.success()
    }

    private fun updateConfigurations() {
        databaseReference.child(DATABASE_PATH_STRING).get()
            .addOnSuccessListener { dataSnapshot ->
                dataSnapshot.getValue<List<Configuration>>()?.forEach { configuration ->
                    Intent().also { intent ->
                        intent.action = INTENT_BROADCAST_ACTION
                        intent.setPackage(configuration.packageId)
                        intent.putExtra(EXTRA_CONFIGURATION, configuration)
                        context.sendBroadcast(intent)
                    }
                }
            }
            .addOnFailureListener {
                Log.e(TAG, "Error getting data: ${it.message}")
            }
    }

    private companion object {
        private const val TAG = "ConfigUpdateWorker"
        const val DATABASE_PATH_STRING = "configurations"
        const val INTENT_BROADCAST_ACTION = "com.configcentre.broadcast.CONFIG_UPDATES"
        const val EXTRA_CONFIGURATION = "extra_configuration"
    }
}
