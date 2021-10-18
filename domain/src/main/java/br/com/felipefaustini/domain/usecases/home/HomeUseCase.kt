package br.com.felipefaustini.domain.usecases.home

import br.com.felipefaustini.domain.models.News
import br.com.felipefaustini.domain.models.NewsBody
import br.com.felipefaustini.domain.models.NewsHeader
import br.com.felipefaustini.domain.models.NewsItem
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.utils.Result

class HomeUseCase(
    private val repository: NewsRepository
): IHomeUseCase {

    override suspend fun getNews(): Result<List<News>> {
        return repository.getNews()
    }

    override fun buildNewsList(data: List<News>): List<NewsItem> {
        val listOfData = arrayListOf<NewsItem>()

        listOfData.add(NewsHeader())

        listOfData.add(NewsBody())

        return listOfData
    }

}