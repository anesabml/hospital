package com.anesabml.hospital.core.data.model

import com.anesabml.hospital.core.model.Sortie
import com.squareup.moshi.Json
import java.util.*

data class SortieEntity(
    @Json(name = "id_sortie")
    val id: Int,
    @Json(name = "type")
    val type: String,
    @Json(name = "date")
    val dateAndTime: Date,
    @Json(name = "dossier_medical")
    val medicalFile: MedicalFileEntity
)

fun SortieEntity.toDomain() =
    Sortie(
        id,
        type,
        dateAndTime,
        medicalFile.toDomain()
    )