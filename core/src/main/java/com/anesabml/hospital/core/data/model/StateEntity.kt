package com.anesabml.hospital.core.data.model

import com.anesabml.hospital.core.model.State
import com.squareup.moshi.Json

data class StateEntity(
    @Json(name = "id_etat")
    val id: Int,
    @Json(name = "temperateur")
    val temperature: Float,
    @Json(name = "pulsation")
    val pulsation: Float,
    @Json(name = "tension")
    val tension: Float,
    @Json(name = "id_dossier")
    val file: MedicalFileEntity
)

fun StateEntity.toDomain() =
    State(
        id,
        temperature,
        pulsation,
        tension,
        file.toDomain()
    )