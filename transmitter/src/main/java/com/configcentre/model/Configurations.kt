package com.configcentre.model

data class Configurations(
    val configurations: List<Configuration> = emptyList()
)

data class Configuration(
    val packageId: String = "",
    val userName: String = "",
    val password: String = "",
    val message: String = ""
)
