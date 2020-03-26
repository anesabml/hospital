package com.anesabml.hospital.profile.data.repository

import com.anesabml.hospital.core.model.Patient
import com.anesabml.hospital.core.utils.Result
import com.anesabml.hospital.profile.data.datasource.ProfileRemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val remoteDataSource: ProfileRemoteDataSource
) {

    fun getProfile(id: Int): Flow<Result<Patient>> {
        // Convert the entities to domain
        return flow {
            emit(Result.Loading)

            /*val entity = remoteDataSource.getMedicalFile(id)

            // Map entity to domain
            emit(Result.Success(entity))*/

            val patient = Patient.empty()
            delay(2000)
            emit(Result.Success(patient))
        }.catch { e ->
            Timber.e(e)
            emit(Result.Error(e))
        }
    }

    fun updatePatient(patient: Patient): Flow<Result<Patient>> {
        // Convert the entities to domain
        return flow {
            emit(Result.Loading)

            /*val entity = remoteDataSource.getMedicalFile(id)

            // Map entity to domain
            emit(Result.Success(entity))*/

            val p = Patient.empty()
            delay(2000)
            emit(Result.Success(p))
        }.catch { e ->
            Timber.e(e)
            emit(Result.Error(e))
        }
    }
}