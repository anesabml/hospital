package com.anesabml.hospital.core.model

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class Appointment(
    val id: Int,
    val patient: Patient,
    val doctor: Doctor,
    val dateAndTime: Date,
    val state: String
) : Serializable {

    fun dateToString(): String =
        SimpleDateFormat("EEE, d MMM", Locale.getDefault()).format(dateAndTime)

    fun dateToTimeString(): String =
        SimpleDateFormat("h:mm a", Locale.getDefault()).format(dateAndTime)
            .toUpperCase(Locale.getDefault())

}