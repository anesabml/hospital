package com.anesabml.hospital.medicalfile.di

import com.anesabml.hospital.medicalfile.ui.PrescriptionFragment
import dagger.Subcomponent

@Subcomponent
interface MedicalFileComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): MedicalFileComponent
    }

    fun inject(prescriptionFragment: PrescriptionFragment)

}