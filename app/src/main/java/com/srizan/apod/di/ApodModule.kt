package com.srizan.apod.di

import android.content.Context
import androidx.room.Room
import com.srizan.apod.api.ApodService
import com.srizan.apod.database.PictureDao
import com.srizan.apod.database.PictureDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : PictureDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            PictureDatabase::class.java,
            "apodCache.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Singleton
    @Provides
    fun provideDao (pictureDatabase: PictureDatabase): PictureDao{
        return pictureDatabase.pictureDao
    }


}