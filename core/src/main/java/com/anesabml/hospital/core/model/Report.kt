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
                    Drug Name 1
                    Drug Name 2 
                    Drug Name 3 
                    Drug Name 4
                    Drug Name 5
                    Drug Name 6
                    Drug Name 7
                    Drug Name 8
                """.trimIndent(),
                Doctor.empty()
            )
    }

    fun dateToString(): String =
        SimpleDateFormat("EEEE dd MMMM", Locale.getDefault()).format(dateAndTime)

}