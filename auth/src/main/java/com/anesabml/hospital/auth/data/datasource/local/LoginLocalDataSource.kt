package com.anesabml.hospital.auth.data.datasource.local

import com.anesabml.hospital.core.data.db.AppDatabase
import com.anesabml.hospital.core.data.model.PatientEntity
import com.anesabml.hospital.core.model.Patient
import javax.inject.Inject

class LoginLocalDataSource @Inject constructor(private val database: AppDatabase) {

    suspend fun insertPatient(patient: Patient) {
        // Insert to the user table first
        database.userDao().insetUser(
            patient.toEntity()
        )

        // Then to the patient table
        database.patientDao().insetPatient(
            patient.toEntity()
        )
    }
}

fun Patient.toEntity(): PatientEntity {
    return PatientEntity(
        id,
        photo,
        firstName,
        lastName,
        username,
        password,
        email,
        phone,
        birthday,
        type,
        state,
        idRoom
    )
}