package br.com.felipefaustini.core.models.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(
    val pagination: PaginationResponse?,
    val data: List<NewsResponseData>?
)

@JsonClass(generateAdapter = true)
data class NewsResponseData(
    val title: String?,
    val description: String?,
    val content: String?,
    val author: String?,
    val published_at: String?,
    val highlight: Boolean?,
    val url: String?,
    val image_url: String?
)