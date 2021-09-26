package br.com.felipefaustini.mesanews.presentation.signin

import androidx.lifecycle.viewModelScope
import br.com.felipefaustini.domain.usecases.ISignInUseCase
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.mesanews.presentation.BaseViewModel
import br.com.felipefaustini.mesanews.utils.EventLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModule(
    private val useCase: ISignInUseCase
): BaseViewModel() {

    var email: String = ""
    var password: String = ""

    private val _signInErrorFieldsLiveData = EventLiveData<Unit>()
    val signInErrorFieldsLiveData: EventLiveData<Unit> = _signInErrorFieldsLiveData

    private val _signInGoHomeLiveData = EventLiveData<Unit>()
    val signInGoHomeLiveData: EventLiveData<Unit> = _signInGoHomeLiveData

    fun signIn() {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                _loadingLiveData.postValue(true)
                when(val result = useCase.signIn(email, password)) {
                    is Result.Success -> {
                        _signInGoHomeLiveData.postValue(Unit)
                    }
                    is Result.Error -> {
                        _errorMessageLiveData.postValue(result.message ?: "")
                    }
                }
                _loadingLiveData.postValue(false)
            }
        } else {
            _signInErrorFieldsLiveData.postValue(Unit)
        }
    }

}