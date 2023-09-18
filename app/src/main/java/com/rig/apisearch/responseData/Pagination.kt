package com.rig.apisearch.responseData

data class Pagination(
    val current_page: Int,
    val limit: Int,
    val offset: Int,
    val total: Int,
    val total_pages: Int
)