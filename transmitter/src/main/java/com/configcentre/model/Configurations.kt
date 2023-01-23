package com.configcentre.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

data class Configurations(
    val configurations: List<Configuration> = emptyList()
)

@Parcelize
data class Configuration(
    @IgnoredOnParcel
    val packageId: String = "",
    val userName: String = "",
    val password: String = "",
    val message: String = ""
): Parcelable
