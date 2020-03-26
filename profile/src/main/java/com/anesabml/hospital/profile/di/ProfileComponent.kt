package com.anesabml.hospital.profile.di

import com.anesabml.hospital.profile.ui.ProfileFragment
import dagger.Subcomponent

@Subcomponent
interface ProfileComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): ProfileComponent
    }

    fun inject(profileFragment: ProfileFragment)

}