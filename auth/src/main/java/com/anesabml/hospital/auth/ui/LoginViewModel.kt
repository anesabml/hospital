package com.anesabml.hospital.auth.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anesabml.hospital.auth.data.repository.LoginRepository
import com.anesabml.hospital.core.model.Patient
import com.anesabml.hospital.core.utils.Event
import com.anesabml.hospital.core.utils.Result
import com.anesabml.hospital.core.utils.Validator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) :
    ViewModel() {

    companion object {
        private const val TAG = "LoginViewModel"
    }

    // Tow way binding
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var username: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    private val _usernameInputErrorEvent = MutableLiveData<Event<Unit>>()
    val usernameInputErrorEvent: LiveData<Event<Unit>> = _usernameInputErrorEvent

    private val _passwordInputErrorEvent = MutableLiveData<Event<Unit>>()
    val passwordInputErrorEvent: LiveData<Event<Unit>> = _passwordInputErrorEvent

    private val _result = MutableLiveData<Event<Result<Patient?>>>()
    val result: LiveData<Event<Result<Patient?>>> = _result

    fun login() {
        var cancel = false
        // Check if the username or password is empty
        if (username.value.isNullOrEmpty()) {
            _usernameInputErrorEvent.value = Event(Unit)
            cancel = true
        }

        if (!Validator.isPasswordValid(password.value)) {
            _passwordInputErrorEvent.value = Event(Unit)
            cancel = true
        }

        if (!cancel) {
            viewModelScope.launch {
                loginRepository.login(
                    username = username.value!!,
                    password = password.value!!
                ).collect {
                    _result.postValue(Event(it))
                }

            }
        }

    }
}