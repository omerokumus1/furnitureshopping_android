package com.omerokumus.furnitureshopping.base.di

import com.omerokumus.furnitureshopping.feature.usermanager.UserManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideUserManager(): UserManager {
        return UserManager()
    }
}