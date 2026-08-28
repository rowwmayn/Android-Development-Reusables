package com.example.ktcounter

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogCEOImage(
    val message: String = "",
    val status: String = ""
)