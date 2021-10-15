package br.com.felipefaustini.core.models.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationResponse(
    val current_page: Int?,
    val per_page: Int?,
    val total_pages: Int?,
    val total_items: Int?
)