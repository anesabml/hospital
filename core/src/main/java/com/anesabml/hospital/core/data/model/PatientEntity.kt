package com.anesabml.hospital.core.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.anesabml.hospital.core.model.Patient
import com.squareup.moshi.Json
import java.io.Serializable
import java.util.*

@Entity(
    tableName = "patients",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
class PatientEntity(
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
    @ColumnInfo
    @Json(name = "etat")
    val state: String,
    @ColumnInfo
    @Json(name = "id_chambre")
    val idRoom: Int
) : UserEntity(
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
), Serializable {
    companion object {
        fun empty() = PatientEntity(
            1,
            "https://cdn.icon-icons.com/icons2/1736/PNG/512/4043260-avatar-male-man-portrait_113269.png",
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

fun PatientEntity.toDomain() =
    Patient(
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
        state,
        idRoom
    )

