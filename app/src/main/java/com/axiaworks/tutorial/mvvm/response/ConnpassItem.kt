package com.axiaworks.tutorial.mvvm.response

import com.squareup.moshi.Json

data class ConnpassEvents(
    @Json(name = "results_start")
    val results_start: Int,
    @Json(name = "results_returned")
    val results_returned: Int,
    @Json(name = "results_available")
    val results_available: Int,
    @Json(name = "events")
    val events_items: List<ConnpassItem>
)

data class ConnpassItem(
    @Json(name = "title")
    val title: String,
    @Json(name = "started_at")
    val started_at: String,
    @Json(name = "owner_display_name")
    val owner_display_name: String
)

