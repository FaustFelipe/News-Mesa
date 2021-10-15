package br.com.felipefaustini.core.models.mappers

import br.com.felipefaustini.core.models.response.NewsResponseData
import br.com.felipefaustini.domain.models.News
import junit.framework.Assert.assertEquals
import org.junit.Test

class NewsMapperTest {

    @Test
    fun map_shouldReturnANewsObject() {
        val expected = News(
            title = "Title",
            description = "Description",
            content = "Content",
            author = "Felipe",
            publishedAt = "",
            highlight = false,
            url = "",
            imageUrl = ""
        )

        val newsResponsedata = NewsResponseData(
            title = "Title",
            description = "Description",
            content = "Content",
            author = "Felipe",
            published_at = null,
            highlight = false,
            url = null,
            image_url = null
        )

        val response = NewsMapper.map(newsResponsedata)

        assertEquals(expected, response)
    }

}