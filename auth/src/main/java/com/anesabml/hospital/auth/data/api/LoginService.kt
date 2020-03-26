package com.anesabml.hospital.auth.data.api

import com.anesabml.hospital.core.data.model.PatientEntity
import retrofit2.http.GET
import retrofit2.http.Query


interface LoginService {

    @GET("login_patient")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): PatientEntity?


}