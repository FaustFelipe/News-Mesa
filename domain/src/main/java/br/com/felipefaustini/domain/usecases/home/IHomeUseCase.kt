package br.com.felipefaustini.domain.usecases.home

import br.com.felipefaustini.domain.models.News
import br.com.felipefaustini.domain.utils.Result

interface IHomeUseCase {
    suspend fun getNews(): Result<List<News>>
}