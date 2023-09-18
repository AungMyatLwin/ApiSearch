package com.rig.apisearch.responseData

data class DataX(
    val _score: Double,
    val api_link: String,
    val api_model: String,
    val id: Int,
    val is_boosted: Boolean,
    val thumbnail: Thumbnail,
    val timestamp: String,
    val title: String
)