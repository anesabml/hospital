package com.anesabml.hospital.core.model

class MedicalFile(
    val id: Int,
    val bloodGroup: String,
    val patient: Patient,
    val rapports: List<Report>
)