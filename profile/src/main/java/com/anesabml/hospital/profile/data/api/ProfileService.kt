package com.anesabml.hospital.profile.data.api

import com.anesabml.hospital.core.data.model.PatientEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface ProfileService {

    @GET("patient")
    suspend fun getPatient(@Query("id") id: Int): PatientEntity

    @PUT("patient")
    suspend fun updatePatient(@Body patientEntity: PatientEntity): PatientEntity
}
