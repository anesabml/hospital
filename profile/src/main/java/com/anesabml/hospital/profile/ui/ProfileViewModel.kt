package com.anesabml.hospital.profile.ui

import androidx.lifecycle.*
import com.anesabml.hospital.core.model.Patient
import com.anesabml.hospital.core.utils.Result
import com.anesabml.hospital.profile.data.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) :
    ViewModel() {


    val photo = MutableLiveData<String>()
    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val phone = MutableLiveData<String>()

    private lateinit var patient: Patient

    private var _state: MutableLiveData<Result<Patient>> = MutableLiveData()
    val state: LiveData<Result<Patient>> = _state

    fun setup(args: ProfileFragmentArgs) {
        patient = args.patient
        photo.value = patient.photo
        firstName.value = patient.firstName
        lastName.value = patient.lastName
        email.value = patient.email
        phone.value = patient.phone
    }

    fun save() {
        viewModelScope.launch {
            val patient = Patient(patient.id, email.value!!, phone.value!!)
            repository.updatePatient(patient).collect {
                _state.postValue(it)
            }
        }
    }
}
