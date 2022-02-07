package com.example.kotlincoroutinesmvvmflow.model

import com.google.gson.annotations.SerializedName


data class FakeResponse(
    @SerializedName("url")
    val url: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)
