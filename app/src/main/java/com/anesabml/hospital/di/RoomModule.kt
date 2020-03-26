package com.anesabml.hospital.di

import android.app.Application
import com.anesabml.hospital.core.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDb(application: Application): AppDatabase =
        AppDatabase.getInstance(application)

}