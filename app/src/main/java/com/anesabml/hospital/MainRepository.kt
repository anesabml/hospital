package com.anesabml.hospital

import android.util.Log
import com.anesabml.hospital.core.data.model.toDomain
import com.anesabml.hospital.core.model.Patient
import com.anesabml.hospital.core.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val mainLocalDataSource: MainLocalDataSource) {

    private val TAG = "MainRepository"

    fun getPatient(): Flow<Result<Patient?>> {
        return flow {
            emit(Result.Loading)

            try {
                val patient = mainLocalDataSource.getPatient()?.toDomain()

                emit(Result.Success(patient))
            } catch (e: Exception) {
                Log.e(TAG, "Error get patient: ", e)
            }
        }
    }

}