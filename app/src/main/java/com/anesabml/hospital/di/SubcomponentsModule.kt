package com.anesabml.hospital.di

import com.anesabml.hospital.appointments.di.AppointmentsComponent
import com.anesabml.hospital.auth.di.LoginComponent
import com.anesabml.hospital.medicalfile.di.MedicalFileComponent
import com.anesabml.hospital.profile.di.ProfileComponent
import dagger.Module

// The "subcomponents" attribute in the @Module annotation tells Dagger what
// Subcomponents are children of the Component this module is included in.
@Module(subcomponents = [LoginComponent::class, AppointmentsComponent::class, MedicalFileComponent::class, ProfileComponent::class])
class SubcomponentsModule