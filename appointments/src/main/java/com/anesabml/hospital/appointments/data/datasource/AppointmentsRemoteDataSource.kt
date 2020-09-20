package com.anesabml.hospital.appointments.data.datasource

import com.anesabml.hospital.appointments.data.api.AppointmentsService
import com.anesabml.hospital.core.data.model.AppointmentEntity
import com.anesabml.hospital.core.data.model.DoctorEntity
import com.anesabml.hospital.core.data.model.PatientEntity
import com.anesabml.hospital.core.model.ServerResponse
import kotlinx.coroutines.delay
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject

class AppointmentsRemoteDataSource @Inject constructor(retrofit: Retrofit) {

    private val appointmentsService = retrofit.create(AppointmentsService::class.java)
    private val appointments = mutableListOf<AppointmentEntity>()
    suspend fun getAppointments(id: Int): List<AppointmentEntity> {
//        appointmentsService.getAppointments(id)
        delay(1000)
        for (i in 0..2) {
            appointments.add(
                AppointmentEntity(
                    i,
                    PatientEntity.empty(),
                    DoctorEntity.empty(),
                    Date(),
                    "Confirmer"
                )
            )
        }
        return appointments
    }

    suspend fun cancelAppointment(id: Int): ServerResponse {
        delay(1_000)
        return ServerResponse()
//        return appointmentsService.cancelAppointment(id)
    }
}
