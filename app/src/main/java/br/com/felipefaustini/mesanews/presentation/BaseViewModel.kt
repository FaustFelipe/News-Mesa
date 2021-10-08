package br.com.felipefaustini.mesanews.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.felipefaustini.mesanews.utils.EventLiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {

    protected val _loadingLiveData = MutableLiveData<Boolean>().apply {
        value = false
    }
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    protected val _errorMessageLiveData = EventLiveData<String>()
    val errorMessageLiveData: EventLiveData<String> = _errorMessageLiveData

    protected fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _loadingLiveData.value = true
                block()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loadingLiveData.value = false
            }
        }
    }

}