package com.anesabml.hospital.appointments.di

import com.anesabml.hospital.appointments.ui.details.AppointmentDetailsFragment
import com.anesabml.hospital.appointments.ui.list.AppointmentsFragment
import dagger.Subcomponent

@Subcomponent
interface AppointmentsComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): AppointmentsComponent
    }

    fun inject(appointmentsFragment: AppointmentsFragment)
    fun inject(appointmentsFragment: AppointmentDetailsFragment)
}