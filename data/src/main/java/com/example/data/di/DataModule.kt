package com.example.data.di

import android.content.Context
import android.content.SharedPreferences
import com.example.data.cache.CityInputCache
import com.example.data.cache.CityInputCacheImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("weather_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideCityInputCache(impl: CityInputCacheImpl): CityInputCache = impl
}