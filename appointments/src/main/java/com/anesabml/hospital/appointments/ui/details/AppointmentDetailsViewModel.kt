package com.anesabml.hospital.appointments.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anesabml.hospital.appointments.data.repository.AppointmentsRepository
import com.anesabml.hospital.core.model.Appointment
import com.anesabml.hospital.core.model.ServerResponse
import com.anesabml.hospital.core.utils.Result
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppointmentDetailsViewModel @Inject constructor(private val repository: AppointmentsRepository) :
    ViewModel() {

    private val _appointment: MutableLiveData<Appointment> = MutableLiveData()
    fun appointment(): LiveData<Appointment> =
        _appointment

    private val _serverResponse: MutableLiveData<Result<ServerResponse>> = MutableLiveData()
    fun serverResponse(): LiveData<Result<ServerResponse>> =
        _serverResponse


    fun setup(args: AppointmentDetailsFragmentArgs) {
        _appointment.value = args.appointment
    }

    fun cancelAppointment() {
        viewModelScope.launch {
            repository.cancelAppointment(_appointment.value!!.id)
                .collect {
                    _serverResponse.value = it
                }
        }
    }
}
