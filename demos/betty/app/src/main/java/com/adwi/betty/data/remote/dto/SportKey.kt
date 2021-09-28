package com.adwi.betty.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SportKeyDto(
    val key: String,
    val group: String,
    val title: String,
    val description: String,
    val active: Boolean = true,
    @SerializedName("has_outrights")
    val hasOutright: Boolean = false
)