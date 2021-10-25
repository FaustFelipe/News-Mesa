package br.com.felipefaustini.mesanews.presentation.home

import br.com.felipefaustini.domain.models.News
import br.com.felipefaustini.domain.usecases.home.IHomeUseCase
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.mesanews.utils.IdleResource
import kotlinx.coroutines.delay

class FakeHomeUseCase: IHomeUseCase {

    private val listNews = listOf(
        News(
            title = "Title News 1",
            description = "Description",
            content = "Content",
            author = "Felipe",
            publishedAt = "",
            highlight = false,
            url = "https://google.com/1",
            imageUrl = ""
        ),
        News(
            title = "Title News 2",
            description = "Description",
            content = "Content",
            author = "Felipe",
            publishedAt = "",
            highlight = false,
            url = "https://google.com/2",
            imageUrl = ""
        ),
        News(
            title = "Title News 3",
            description = "Description",
            content = "Content",
            author = "Felipe",
            publishedAt = "",
            highlight = false,
            url = "https://google.com/3",
            imageUrl = ""
        ),
        News(
            title = "Title News 4",
            description = "Description",
            content = "Content",
            author = "Felipe",
            publishedAt = "",
            highlight = false,
            url = "https://google.com/4",
            imageUrl = ""
        ),
        News(
            title = "Title News 5",
            description = "Description",
            content = "Content",
            author = "Felipe",
            publishedAt = "",
            highlight = false,
            url = "https://google.com/5",
            imageUrl = ""
        ),
        News(
            title = "Title News 6",
            description = "Description",
            content = "Content",
            author = "Felipe",
            publishedAt = "",
            highlight = false,
            url = "https://google.com/6",
            imageUrl = ""
        )
    )

    override suspend fun getHighlights(): Result<List<News>> {
        IdleResource.instanceHighlights.increment()
        delay(1_000L)
        IdleResource.instanceHighlights.decrement()
        return Result.Success(listNews)
    }

    override suspend fun getNews(): Result<List<News>> {
        IdleResource.instanceNews.increment()
        delay(1_000L)
        IdleResource.instanceNews.decrement()
        return Result.Success(listNews)
    }

}