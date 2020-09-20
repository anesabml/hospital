package com.anesabml.hospital.auth.data.datasource.remote

import com.anesabml.hospital.auth.data.api.LoginService
import com.anesabml.hospital.core.data.model.PatientEntity
import com.anesabml.hospital.core.data.model.toDomain
import com.anesabml.hospital.core.model.Patient
import kotlinx.coroutines.delay
import retrofit2.Retrofit
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(retrofit: Retrofit) {

    private val loginService = retrofit.create(LoginService::class.java)

    suspend fun login(username: String, password: String): Patient? {
        // fixme
//        loginService.login(username, password).body()?.toDomain()
        delay(1000)
        return if (username.isEmpty() || username == "anesabml" || password.isEmpty()) {
            null
        } else {
            PatientEntity.empty().toDomain()
        }
    }
}