package com.anesabml.hospital.medicalfile.data.api

import com.anesabml.hospital.core.data.model.ReportEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface MedicalFileService {

    @GET("prescriptions")
    suspend fun getLastPrescription(@Query("id") id: Int): ReportEntity
}
