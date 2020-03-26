package com.anesabml.hospital.auth.data.repository

import com.anesabml.hospital.auth.data.datasource.local.LoginLocalDataSource
import com.anesabml.hospital.auth.data.datasource.remote.LoginRemoteDataSource
import com.anesabml.hospital.core.model.Patient
import com.anesabml.hospital.core.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource,
    private val localDataSource: LoginLocalDataSource
) {

    suspend fun login(username: String, password: String): Flow<Result<Patient?>> {
        return flow {

            emit(Result.Loading)

            val patient = remoteDataSource.login(username, password)

            // Save the patient to the db and update the ui
            patient?.let { localDataSource.insertPatient(it) }

            emit(Result.Success(patient))
        }.catch { e ->
            Timber.e(e)
            emit(Result.Error(e))
        }
    }
}