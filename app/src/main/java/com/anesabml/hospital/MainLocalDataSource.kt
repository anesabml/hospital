package com.anesabml.hospital

import com.anesabml.hospital.core.data.db.AppDatabase
import javax.inject.Inject

class MainLocalDataSource @Inject constructor(private val db: AppDatabase) {

    suspend fun getPatient() =
        db.patientDao().getPatient()
}