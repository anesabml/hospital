package com.anesabml.hospital.core.data.db

import androidx.room.Dao
import androidx.room.Insert
import com.anesabml.hospital.core.data.model.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insetUser(userEntity: UserEntity)
}