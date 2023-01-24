package com.configcentre.projecta.util

import android.content.Context

private const val SHARED_PREFERENCE_NAME = "MY_SHARED_PREFERENCES"

const val EXTRA_CONFIGURATION = "extra_configuration"

class SharedPreferenceManager(context: Context) {

    private var sharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun putString(prefKey: String, prefValue: String) {
        with(sharedPreferences.edit()) {
            putString(prefKey, prefValue)
            commit()
        }
    }

    fun getString(prefKey: String, defaultValue: String = "") =
        sharedPreferences.getString(prefKey, defaultValue)
}
