package com.srizan.apod.di

import com.srizan.apod.api.ApodService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApodModule {
    @Provides
    fun provideAnalyticsService(): ApodService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.nasa.gov")
            .build()
            .create(ApodService::class.java)
    }
}