package com.anesabml.hospital.appointments.ui.list

import androidx.lifecycle.*
import com.anesabml.hospital.appointments.data.repository.AppointmentsRepository
import com.anesabml.hospital.core.model.Appointment
import com.anesabml.hospital.core.model.Patient
import com.anesabml.hospital.core.utils.Result
import javax.inject.Inject

class AppointmentsViewModel @Inject constructor(
    private val repository: AppointmentsRepository
) : ViewModel() {

    private val patient = MutableLiveData<Patient>()

    val appointmentsItems: LiveData<Result<List<Appointment>>> = patient.switchMap {
        repository.getAppointments(it.id).asLiveData(viewModelScope.coroutineContext)
    }

    fun setup(args: AppointmentsFragmentArgs) {
        patient.value = args.patient
    }

}
