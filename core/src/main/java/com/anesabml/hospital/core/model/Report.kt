package com.anesabml.hospital.core.model

import java.text.SimpleDateFormat
import java.util.*

class Report(
    val id: Int,
    val medicalFileId: Int,
    val dateAndTime: Date,
    val type: ReportType,
    val content: String,
    val doctor: Doctor
) {

    companion object {
        fun report() =
            Report(
                1,
                1,
                Date(),
                ReportType.Report,
                "Content Content Content Content",
                Doctor.empty()
            )

        fun prescription() =
            Report(
                1,
                1,
                Date(),
                ReportType.Ordonnance,
                """
                    Medication Name 1
                    Medication Name 2 
                    Medication Name 3 
                    Medication Name 4
                """.trimIndent(),
                Doctor.empty()
            )
    }

    fun dateToString(): String =
        SimpleDateFormat("EEEE dd MMMM", Locale.getDefault()).format(dateAndTime)

}