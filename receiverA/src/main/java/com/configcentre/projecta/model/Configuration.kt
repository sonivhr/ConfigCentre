package com.configcentre.projecta.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Configuration(
    val userName: String = "",
    val password: String = "",
    val message: String = ""
): Parcelable
