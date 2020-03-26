package com.anesabml.hospital.auth.di

import com.anesabml.hospital.auth.ui.LoginActivity
import dagger.Subcomponent

@Subcomponent
@FeatureScope
interface LoginComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(activity: LoginActivity)

}
