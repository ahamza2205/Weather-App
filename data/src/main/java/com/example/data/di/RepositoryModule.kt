package com.example.data.di

import com.example.core.serveice.WeatherApiService
import com.example.data.repository.WeatherRepository
import com.example.data.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideWeatherRepository(api: WeatherApiService): WeatherRepository =
        WeatherRepositoryImpl(api)
}