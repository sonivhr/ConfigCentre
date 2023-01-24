package com.configcentre.projecta.util

import android.content.Context

private const val SHARED_PREFERENCE_NAME = "_SHARED_PREFERENCES"

const val EXTRA_CONFIGURATION = "extra_configuration"

class SharedPreferenceManager(context: Context) {

    private var sharedPreferences =
        context.getSharedPreferences(
            /* name = */ "${context.packageName}$SHARED_PREFERENCE_NAME",
            /* mode = */ Context.MODE_PRIVATE
        )

    fun putString(prefKey: String, prefValue: String) {
        with(sharedPreferences.edit()) {
            putString(prefKey, prefValue)
            commit()
        }
    }

    fun getString(prefKey: String, defaultValue: String = "") =
        sharedPreferences.getString(prefKey, defaultValue)
}
