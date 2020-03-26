package com.anesabml.hospital

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.anesabml.hospital.core.model.Patient
import com.anesabml.hospital.core.utils.Result
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _patient: LiveData<Result<Patient?>> =
        mainRepository
            .getPatient()
            .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)


    val patient: LiveData<Result<Patient?>> = _patient
}