package com.anesabml.hospital.core.data.db

import androidx.room.TypeConverter
import java.sql.Time
import java.util.*

class Converters {
    @TypeConverter
    fun dateToDatestamp(date: Date): Long = date.time

    @TypeConverter
    fun datestampToDate(value: Long): Date =
        Date(value)

    @TypeConverter
    fun timeToDatestamp(time: Time): Long = time.time

    @TypeConverter
    fun datestampTotime(value: Long): Time =
        Time(value)
}