package com.configcentre.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Configurations(
    val configurations: List<Configuration> = emptyList()
)

@Parcelize
data class Configuration(
    @Transient
    val packageId: String = "",
    val userName: String = "",
    val password: String = "",
    val message: String = ""
): Parcelable
