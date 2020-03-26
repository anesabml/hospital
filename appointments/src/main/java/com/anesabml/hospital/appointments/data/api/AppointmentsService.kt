package com.anesabml.hospital.appointments.data.api

import com.anesabml.hospital.core.data.model.AppointmentEntity
import com.anesabml.hospital.core.model.ServerResponse
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface AppointmentsService {

    @GET("appointments")
    suspend fun getAppointments(@Query("id") id: Int): List<AppointmentEntity>

    @PUT("cancel_appointment")
    suspend fun cancelAppointment(@Query("id") id: Int): ServerResponse

}
