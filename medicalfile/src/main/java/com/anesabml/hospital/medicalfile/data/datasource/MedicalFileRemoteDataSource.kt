package com.anesabml.hospital.medicalfile.data.datasource

import com.anesabml.hospital.core.data.model.ReportEntity
import com.anesabml.hospital.medicalfile.data.api.MedicalFileService
import retrofit2.Retrofit
import javax.inject.Inject

class MedicalFileRemoteDataSource @Inject constructor(retrofit: Retrofit) {

    private val medicalFileService = retrofit.create(MedicalFileService::class.java)

    suspend fun getLastPrescription(id: Int): ReportEntity {
        return medicalFileService.getLastPrescription(id)
    }

}
