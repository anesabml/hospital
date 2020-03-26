package com.anesabml.hospital.core.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anesabml.hospital.core.model.User
import com.squareup.moshi.Json
import java.io.Serializable
import java.util.*

@Entity(tableName = "users")
open class UserEntity(
    @PrimaryKey
    @Json(name = "id_utilisateur")
    val id: Int,
    @ColumnInfo
    @Json(name = "photo")
    val photo: String,
    @ColumnInfo
    @Json(name = "nom")
    val firstName: String,
    @ColumnInfo
    @Json(name = "prenom")
    val lastName: String,
    @ColumnInfo
    @Json(name = "username")
    val username: String,
    @ColumnInfo
    @Json(name = "password")
    val password: String,
    @ColumnInfo
    @Json(name = "email")
    val email: String,
    @ColumnInfo
    @Json(name = "numeroTel")
    val phone: String,
    @ColumnInfo
    @Json(name = "dateNaissance")
    val birthday: Date,
    @ColumnInfo
    @Json(name = "type")
    val type: String
) : Serializable

fun UserEntity.toDomain() =
    User(
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
    )