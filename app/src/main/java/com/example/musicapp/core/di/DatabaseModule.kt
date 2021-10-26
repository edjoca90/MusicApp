package com.example.musicapp.core.di


import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    /*@Singleton
    @Provides
    fun provideDataBase(app: Application) : AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "app_database").build();
    }*/

}
