package com.anesabml.hospital

import android.app.Application
import com.anesabml.hospital.appointments.di.AppointmentsComponent
import com.anesabml.hospital.appointments.di.AppointmentsComponentProvider
import com.anesabml.hospital.auth.di.LoginComponent
import com.anesabml.hospital.auth.di.LoginComponentProvider
import com.anesabml.hospital.di.DaggerAppComponent
import com.anesabml.hospital.medicalfile.di.MedicalFileComponent
import com.anesabml.hospital.medicalfile.di.MedicalFileComponentProvider
import com.anesabml.hospital.profile.di.ProfileComponent
import com.anesabml.hospital.profile.di.ProfileComponentProvider
import timber.log.Timber

class MyApplication : Application(),
    LoginComponentProvider,
    AppointmentsComponentProvider,
    MedicalFileComponentProvider,
    ProfileComponentProvider {

    val appComponent = DaggerAppComponent.builder().application(this).build()

    override fun provideLoginComponent(): LoginComponent =
        appComponent.loginComponent().create()

    override fun provideAppointmentsComponent(): AppointmentsComponent =
        appComponent.appointmentsComponent().create()

    override fun provideMedicalFileComponent(): MedicalFileComponent =
        appComponent.medicalFileComponent().create()

    override fun provideProfileComponent(): ProfileComponent =
        appComponent.profileComponent().create()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}