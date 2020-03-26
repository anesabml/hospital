package com.anesabml.hospital.di

import android.app.Application
import com.anesabml.hospital.MainActivity
import com.anesabml.hospital.appointments.di.AppointmentsComponent
import com.anesabml.hospital.auth.di.LoginComponent
import com.anesabml.hospital.medicalfile.di.MedicalFileComponent
import com.anesabml.hospital.profile.di.ProfileComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


// Definition of the Application graph
// The "modules" attribute in the @Component annotation tells Dagger what Modules
// to include when building the graph
@Singleton
@Component(modules = [SubcomponentsModule::class, RoomModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun loginComponent(): LoginComponent.Factory

    fun appointmentsComponent(): AppointmentsComponent.Factory

    fun medicalFileComponent(): MedicalFileComponent.Factory

    fun profileComponent(): ProfileComponent.Factory

}