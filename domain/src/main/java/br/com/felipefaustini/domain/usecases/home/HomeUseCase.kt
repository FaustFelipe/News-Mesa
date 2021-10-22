package br.com.felipefaustini.domain.usecases.home

import br.com.felipefaustini.domain.models.News
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.utils.Result

class HomeUseCase(
    private val repository: NewsRepository
): IHomeUseCase {

    override suspend fun getHighlights(): Result<List<News>> {
        return repository.getHighlights()
    }

    override suspend fun getNews(): Result<List<News>> {
        return repository.getNews()
    }

}