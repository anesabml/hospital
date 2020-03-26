package com.anesabml.hospital.appointments.data.repository

import com.anesabml.hospital.appointments.data.datasource.AppointmentsRemoteDataSource
import com.anesabml.hospital.core.data.model.toDomain
import com.anesabml.hospital.core.model.Appointment
import com.anesabml.hospital.core.model.ServerResponse
import com.anesabml.hospital.core.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class AppointmentsRepository @Inject constructor(
    private val remoteDataSource: AppointmentsRemoteDataSource
) {

    fun getAppointments(id: Int): Flow<Result<List<Appointment>>> {
        // Convert the entities to domain
        return flow {
            emit(Result.Loading)
            val entities = remoteDataSource.getAppointments(id)

            // Map entities to domain
            val list = mutableListOf<Appointment>()
            for (entity in entities) {
                list.add(entity.toDomain())
            }
            emit(Result.Success(list))
        }.catch { e ->
            Timber.e(e)
            emit(Result.Error(e))
        }
    }

    fun cancelAppointment(id: Int): Flow<Result<ServerResponse>> {
        return flow {
            emit(Result.Loading)
            val serverResponse = remoteDataSource.cancelAppointment(id)
            emit(Result.Success(serverResponse))
        }.catch { e ->
            Timber.e(e)
            emit(Result.Error(e))
        }
    }

}