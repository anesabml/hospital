package com.anesabml.hospital.core.data.model

import com.anesabml.hospital.core.model.Appointment
import com.squareup.moshi.Json
import java.util.*

data class AppointmentEntity(
    @Json(name = "id_rendez_vous")
    val id: Int,
    @Json(name = "patient")
    val patient: PatientEntity,
    @Json(name = "medecin")
    val doctor: DoctorEntity,
    @Json(name = "date")
    val dateAndTime: Date,
    @Json(name = "etat")
    val state: String
)

fun AppointmentEntity.toDomain() =
    Appointment(
        id,
        patient.toDomain(),
        doctor.toDomain(),
        dateAndTime,
        state
    )
