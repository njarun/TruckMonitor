package com.dxb.truckmonitor.di.module

import android.content.Context
import androidx.room.Room
import com.dxb.truckmonitor.data.database.AppDatabase
import com.dxb.truckmonitor.data.database.dao.TruckDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "app_storage_bucket"

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    fun provideTruckDao(appDatabase: AppDatabase): TruckDao {
        return appDatabase.truckDao()
    }
}