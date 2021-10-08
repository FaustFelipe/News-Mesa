package br.com.felipefaustini.mesanews.presentation.signup

import br.com.felipefaustini.domain.usecases.signup.SignUpUseCase
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.mesanews.presentation.BaseViewModel
import br.com.felipefaustini.mesanews.utils.EventLiveData

class SignUpViewModel(
    private val useCase: SignUpUseCase
): BaseViewModel() {

    var name: String = ""
    var email: String = ""
    var password: String = ""

    private val _errorFieldsEmpty = EventLiveData<Unit>()
    val errorFieldsEmpty = _errorFieldsEmpty

    private val _signUpGoHomeLiveData = EventLiveData<Unit>()
    val signUpGoHomeLiveData: EventLiveData<Unit> = _signUpGoHomeLiveData

    fun signUp() {
        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            launchDataLoad {
                when(val result = useCase.signUp(name, email, password)) {
                    is Result.Success -> {
                        _signUpGoHomeLiveData.postValue(Unit)
                    }
                    is Result.Error -> {
                        _errorMessageLiveData.postValue("Erro")
                    }
                }
            }
        } else {
            _errorFieldsEmpty.postValue(Unit)
        }
    }

}