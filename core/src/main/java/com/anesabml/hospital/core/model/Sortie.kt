package com.anesabml.hospital.core.model

import java.util.*

class Sortie(
    val id: Int,
    val type: String,
    val dateAndTime: Date,
    val medicalFile: MedicalFile
)