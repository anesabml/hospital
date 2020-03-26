package com.anesabml.hospital.core.data.model

import com.anesabml.hospital.core.model.Doctor
import com.anesabml.hospital.core.model.Report
import com.anesabml.hospital.core.model.ReportType
import com.squareup.moshi.Json
import java.util.*

data class ReportEntity(
    @Json(name = "id_raport")
    val id: Int,
    @Json(name = "dossier_medical")
    val medicalFileId: Int,
    @Json(name = "date")
    val dateAndTime: Date,
    @Json(name = "type")
    val type: ReportType,
    @Json(name = "contenu")
    val content: String,
    @Json(name = "medcin")
    val doctor: Doctor
)

fun ReportEntity.toDomain() =
    Report(
        id,
        medicalFileId,
        dateAndTime,
        type,
        content,
        doctor
    )