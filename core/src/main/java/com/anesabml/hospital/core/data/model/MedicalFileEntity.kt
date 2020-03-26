package com.anesabml.hospital.core.data.model

import com.anesabml.hospital.core.model.MedicalFile
import com.anesabml.hospital.core.model.Patient
import com.squareup.moshi.Json

data class MedicalFileEntity(
    @Json(name = "id_dossier")
    val id: Int,
    @Json(name = "groupage")
    val bloodGroup: String,
    @Json(name = "patient")
    val patient: Patient,
    @Json(name = "rapports")
    val rapports: List<ReportEntity>
)

fun MedicalFileEntity.toDomain() =
    MedicalFile(
        id,
        bloodGroup,
        patient,
        rapports.map { it.toDomain() }
    )