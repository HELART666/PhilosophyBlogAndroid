package com.example.philosophyblog.di.modules

import android.content.Context
import com.example.philosophyblog.data.sharedprefs.AuthSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalStorageModule {


    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context) =
        AuthSharedPreferences(context = context)

}