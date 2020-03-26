package com.anesabml.hospital.core.model

import java.util.*

class Patient(
    id: Int,
    photo: String,
    firstName: String,
    lastName: String,
    username: String,
    password: String,
    email: String,
    phone: String,
    birthday: Date,
    type: String,
    val state: String,
    val idRoom: Int
) : User(
    id,
    photo,
    firstName,
    lastName,
    username,
    password,
    email,
    phone,
    birthday,
    type
) {
    constructor(id: Int, email: String, phone: String) : this(
        id,
        "",
        "",
        "",
        "",
        "",
        email,
        phone,
        Date(),
        "",
        "",
        -1
    )

    companion object {
        fun empty() = Patient(
            1,
            "",
            "Anes",
            "Abismail",
            "anesabml",
            "1234",
            "anes@abi.com",
            "05",
            Date(),
            "Patient",
            "No hospitaliser",
            1
        )
    }
}