package com.anesabml.hospital.profile.data.datasource

import com.anesabml.hospital.core.data.model.PatientEntity
import com.anesabml.hospital.profile.data.api.ProfileService
import retrofit2.Retrofit
import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(retrofit: Retrofit) {

    private val apiService = retrofit.create(ProfileService::class.java)

    suspend fun getPatient(id: Int): PatientEntity {
        return apiService.getPatient(id)
    }

    suspend fun updatePatient(patientEntity: PatientEntity): PatientEntity {
        return apiService.updatePatient(patientEntity)
    }

}
