package com.anesabml.hospital.medicalfile.ui

import androidx.lifecycle.*
import com.anesabml.hospital.core.model.Patient
import com.anesabml.hospital.core.model.Report
import com.anesabml.hospital.core.utils.Result
import com.anesabml.hospital.medicalfile.data.repository.MedicalFileRepository
import javax.inject.Inject

class PrescriptionViewModel @Inject constructor(private val repository: MedicalFileRepository) :
    ViewModel() {

    private val patient = MutableLiveData<Patient>()

    val state: LiveData<Result<Report>> = patient.switchMap {
        repository.getLastPrescription(it.id).asLiveData(viewModelScope.coroutineContext)
    }

    val isLoading: LiveData<Boolean> = state.map {
        it is Result.Loading
    }

    fun setup(args: PrescriptionFragmentArgs) {
        patient.value = args.patient
    }

}
