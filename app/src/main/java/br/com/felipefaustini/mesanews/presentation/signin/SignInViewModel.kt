package br.com.felipefaustini.mesanews.presentation.signin

import br.com.felipefaustini.domain.usecases.signin.SignInUseCase
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.mesanews.presentation.BaseViewModel
import br.com.felipefaustini.mesanews.utils.EventLiveData

class SignInViewModel(
    private val useCase: SignInUseCase
): BaseViewModel() {

    var email: String = ""
    var password: String = ""

    private val _signInErrorFieldsLiveData = EventLiveData<Unit>()
    val signInErrorFieldsLiveData: EventLiveData<Unit> = _signInErrorFieldsLiveData

    private val _signInGoHomeLiveData = EventLiveData<Unit>()
    val signInGoHomeLiveData: EventLiveData<Unit> = _signInGoHomeLiveData

    fun signIn() {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            launchDataLoad {
                when(val result = useCase.signIn(email, password)) {
                    is Result.Success -> {
                        _signInGoHomeLiveData.postValue(Unit)
                    }
                    is Result.Error -> {
                        _errorMessageLiveData.postValue("Erro")
                    }
                }
            }
        } else {
            _signInErrorFieldsLiveData.postValue(Unit)
        }
    }

}