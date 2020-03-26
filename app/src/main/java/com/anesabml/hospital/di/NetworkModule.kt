package com.anesabml.hospital.di

import com.anesabml.hospital.core.BuildConfig
import com.anesabml.hospital.core.data.api.ApiService.retrofitClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

// @Module informs Dagger that this class is a Dagger Module
@Module
class NetworkModule {

    // @Provides tell Dagger how to create instances of the type that this function
    // returns (i.e. LoginService).
    // Function parameters are the dependencies of this type.
    @Singleton
    @Provides
    fun provideLoginService(): Retrofit {
        // Whenever Dagger needs to provide an instance of type Retrofit,
        // this code (the one inside the @Provides method) is run.
        return retrofitClient(BuildConfig.DEBUG)
    }

}