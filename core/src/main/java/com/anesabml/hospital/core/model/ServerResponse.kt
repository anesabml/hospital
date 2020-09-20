package com.anesabml.hospital.core.model

data class ServerResponse(
    val fieldCount: Int = 0,
    val affectedRows: Int = 0,
    val insertId: Int = 0,
    val serverStatus: Int = 0,
    val warningCount: Int = 0,
    val message: String = "",
    val protocol41: Boolean = false,
    val changedRows: Int = 0
)
