package com.rig.apisearch.responseData

data class data(
    val config: Config,
    val `data`: List<DataX>,
    val info: Info,
    val pagination: Pagination,
    val preference: Any
)