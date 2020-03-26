package com.anesabml.hospital.medicalfile.data.repository

import com.anesabml.hospital.core.model.Report
import com.anesabml.hospital.core.utils.Result
import com.anesabml.hospital.medicalfile.data.datasource.MedicalFileRemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class MedicalFileRepository @Inject constructor(
    private val remoteDataSource: MedicalFileRemoteDataSource
) {

    fun getLastPrescription(id: Int): Flow<Result<Report>> {
        return flow {
            emit(Result.Loading)

            // Fixme
            //val prescription = remoteDataSource.getLastPrescription(id).toDomain()

            delay(2000)
            val prescription = Report.prescription()
            emit(Result.Success(prescription))
        }.catch { e ->
            Timber.e(e)
            emit(Result.Error(e))
        }
    }
}