package br.com.felipefaustini.mesanews.presentation.signup

import androidx.lifecycle.viewModelScope
import br.com.felipefaustini.domain.usecases.signup.ISignUpUseCase
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.mesanews.presentation.BaseViewModel
import br.com.felipefaustini.mesanews.utils.EventLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val useCase: ISignUpUseCase
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
            viewModelScope.launch(Dispatchers.IO) {
                _loadingLiveData.postValue(true)
                when(val result = useCase.signUp(name, email, password)) {
                    is Result.Success -> {
                        _signUpGoHomeLiveData.postValue(Unit)
                    }
                    is Result.Error -> {
                        _errorMessageLiveData.postValue(result.message ?: "")
                    }
                }
                _loadingLiveData.postValue(false)
            }
        } else {
            _errorFieldsEmpty.postValue(Unit)
        }
    }

}