package com.dxb.truckmonitor.di.module

import com.dxb.truckmonitor.data.router.api.TruckMonitorApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideTruckMonitorApi(retrofit: Retrofit): TruckMonitorApi =
        retrofit.create(TruckMonitorApi::class.java)
}