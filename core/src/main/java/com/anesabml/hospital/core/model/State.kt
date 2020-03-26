package com.anesabml.hospital.core.model

class State(
    val id: Int,
    val temperature: Float,
    val pulsation: Float,
    val tension: Float,
    val file: MedicalFile
)