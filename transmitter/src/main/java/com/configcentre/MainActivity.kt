package com.configcentre

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.configcentre.databinding.ActivityMainBinding
import com.configcentre.model.Configurations
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.simpleName
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database.reference
    }

    override fun onResume() {
        super.onResume()
        listenToRealtimeDataChanges()
    }

    override fun onPause() {
        super.onPause()
        database.removeEventListener(postListener)
    }

    private fun listenToRealtimeDataChanges() {
        database.addValueEventListener(postListener)
    }

    private val postListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            dataSnapshot.getValue<Configurations>()?.run {
                binding.tvDataUpdate.text = ""
                configurations.forEach {
                    with(binding.tvDataUpdate) {
                        append(
                            "PackageId: ${it.packageId}\nUsername: ${it.userName}\nPassword: ${it.password}\nMessage: ${it.message}\n\n"
                        )
                    }
                }
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Post failed, log a message
            Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
        }
    }
}

