package br.com.felipefaustini.core.models.mappers

import br.com.felipefaustini.core.models.response.NewsResponseData
import br.com.felipefaustini.domain.models.News

object NewsMapper {

    fun map(newsResponseData: NewsResponseData) = News(
        title = newsResponseData.title ?: "",
        description = newsResponseData.description ?: "",
        content = newsResponseData.content ?: "",
        author = newsResponseData.author ?: "",
        publishedAt = newsResponseData.published_at ?: "",
        highlight = newsResponseData.highlight ?: false,
        url = newsResponseData.url ?: "",
        imageUrl = newsResponseData.image_url ?: ""
    )

}