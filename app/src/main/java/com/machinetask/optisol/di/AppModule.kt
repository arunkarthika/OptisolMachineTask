package com.machinetask.optisol.di

import android.content.Context
import androidx.room.Room
import com.machinetask.optisol.Dao.FeedDao
import com.machinetask.optisol.Database.FeedDatabase
import com.machinetask.optisol.Repository.FeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule  {

    @Provides
    fun providesUserDao(userDatabase: FeedDatabase): FeedDao = userDatabase.feedDao()

    @Provides
    @Singleton
    fun providesUserDatabase(@ApplicationContext context: Context):FeedDatabase
            = Room.databaseBuilder(context,FeedDatabase::class.java,"FeedDatabase").build()

    @Provides
    fun providesUserRepository(userDao: FeedDao) : FeedRepository
            = FeedRepository(userDao)
}