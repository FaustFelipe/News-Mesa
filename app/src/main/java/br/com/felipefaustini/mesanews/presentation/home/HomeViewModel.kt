package br.com.felipefaustini.mesanews.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.felipefaustini.domain.models.News
import br.com.felipefaustini.domain.models.NewsItem
import br.com.felipefaustini.domain.usecases.home.IHomeUseCase
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.mesanews.presentation.BaseViewModel

class HomeViewModel(
    private val useCase: IHomeUseCase
): BaseViewModel() {

    private val _listNewsLiveData: MutableLiveData<List<NewsItem>> = MutableLiveData()
    val listNewsLiveData: LiveData<List<NewsItem>> = _listNewsLiveData

    fun listNews() {
        launchDataLoad {
            when(val result = useCase.getNews()) {
                is Result.Success -> {
                    val data = useCase.buildNewsList(result.data)
                    _listNewsLiveData.postValue(data)
                }
                is Result.Error -> {
                    _errorMessageLiveData.postValue("Erro")
                }
            }
        }
    }

}