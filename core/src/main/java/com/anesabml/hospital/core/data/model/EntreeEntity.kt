package com.anesabml.hospital.core.data.model

import com.anesabml.hospital.core.model.Entree
import com.squareup.moshi.Json
import java.util.*

data class EntreeEntity(
    @Json(name = "id_entre")
    val id: Int,
    @Json(name = "type")
    val type: String,
    @Json(name = "date")
    val dateAndTime: Date,
    @Json(name = "dossier_medical")
    val medicalFile: MedicalFileEntity
)

fun EntreeEntity.toDomain() =
    Entree(
        id,
        type,
        dateAndTime,
        medicalFile.toDomain()
    )