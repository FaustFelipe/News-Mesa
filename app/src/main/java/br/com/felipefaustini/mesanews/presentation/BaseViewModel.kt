package br.com.felipefaustini.mesanews.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.felipefaustini.mesanews.utils.EventLiveData

open class BaseViewModel: ViewModel() {

    protected val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    protected val _errorMessageLiveData = EventLiveData<String>()
    val errorMessageLiveData: EventLiveData<String> = _errorMessageLiveData

}