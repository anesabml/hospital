package com.anesabml.hospital.core.model

import java.io.Serializable
import java.util.*

open class User(
    val id: Int,
    val photo: String,
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String,
    val email: String,
    val phone: String,
    val birthday: Date,
    val type: String
) : Serializable