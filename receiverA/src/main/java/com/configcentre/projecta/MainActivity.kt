package com.configcentre.projecta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.configcentre.projecta.databinding.ActivityMainBinding
import com.configcentre.projecta.model.Configuration
import com.configcentre.projecta.util.EXTRA_CONFIGURATION
import com.configcentre.projecta.util.SharedPreferenceManager
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val sharedPreferenceManager by lazy {
        SharedPreferenceManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showConfig()
    }

    private fun showConfig() {
        val configurationString = sharedPreferenceManager.getString(EXTRA_CONFIGURATION)
        if (!configurationString.isNullOrEmpty()) {
            Gson().fromJson(configurationString, Configuration::class.java).run {
                binding.tvConfig.text =
                    "Username: $userName\nPassword: $password\nMessage: $message"
            }
        }
    }
}