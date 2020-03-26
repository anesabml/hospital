package com.anesabml.hospital.core.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anesabml.hospital.core.data.model.PatientEntity

@Dao
interface PatientDao {

    @Insert
    suspend fun insetPatient(patient: PatientEntity)

    @Query("SELECT * FROM patients, users WHERE users.id = patients.id LIMIT 1")
    suspend fun getPatient(): PatientEntity?
}