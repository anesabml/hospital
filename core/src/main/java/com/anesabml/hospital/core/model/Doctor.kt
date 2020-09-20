package com.anesabml.hospital.core.model

import java.util.*

class Doctor(
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
    val speciality: String
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

    companion object {
        fun empty() = Doctor(
            1,
            "https://www.nj.com/resizer/h8MrN0-Nw5dB5FOmMVGMmfVKFJo=/450x0/smart/cloudfront-us-east-1.images.arcpublishing.com/advancelocal/SJGKVE5UNVESVCW7BBOHKQCZVE.jpg",
            "Saoudi",
            "Mohamed",
            "anesabml",
            "1234",
            "anes@abi.com",
            "05",
            Date(),
            "Doctor",
            "Dontiste"
        )
    }
}