package br.com.felipefaustini.mesanews.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.felipefaustini.domain.models.News
import br.com.felipefaustini.domain.usecases.home.IHomeUseCase
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.mesanews.presentation.BaseViewModel

class HomeViewModel(
    private val useCase: IHomeUseCase
): BaseViewModel() {

    private val _listHighlightsLiveData: MutableLiveData<List<News>> = MutableLiveData()
    val listHighlightsLiveData: LiveData<List<News>> = _listHighlightsLiveData

    private val _listNewsLiveData: MutableLiveData<List<News>> = MutableLiveData()
    val listNewsLiveData: LiveData<List<News>> = _listNewsLiveData

    fun listHighlights() {
        launchDataLoad {
            when(val result = useCase.getHighlights()) {
                is Result.Success -> {
                    _listHighlightsLiveData.postValue(result.data)
                }
                is Result.Error -> {
                    _errorMessageLiveData.postValue("Erro")
                }
            }
        }
    }

    fun listNews() {
        launchDataLoad {
            when(val result = useCase.getNews()) {
                is Result.Success -> {
                    _listNewsLiveData.postValue(result.data)
                }
                is Result.Error -> {
                    _errorMessageLiveData.postValue("Erro")
                }
            }
        }
    }

}