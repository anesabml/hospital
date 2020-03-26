package com.anesabml.hospital.core.data.model

import com.anesabml.hospital.core.model.Doctor
import com.squareup.moshi.Json
import java.util.*

data class DoctorEntity(
    @Json(name = "id_utilisateur")
    val id: Int,
    @Json(name = "photo")
    val photo: String,
    @Json(name = "nom")
    val firstName: String,
    @Json(name = "prenom")
    val lastName: String,
    @Json(name = "username")
    val username: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "numeroTel")
    val phone: String,
    @Json(name = "dateNaissance")
    val birthday: Date,
    @Json(name = "type")
    val type: String,
    @Json(name = "speiciality")
    val speciality: String
) {
    companion object {
        fun empty() = DoctorEntity(
            1,
            "https://images.unsplash.com/photo-1499996860823-5214fcc65f8f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=900&q=60",
            "Anes",
            "Abismail",
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

fun DoctorEntity.toDomain() =
    Doctor(
        id,
        photo,
        firstName,
        lastName,
        username,
        password,
        email,
        phone,
        birthday,
        type,
        speciality
    )