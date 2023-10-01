package com.dxb.truckmonitor.di.module

import com.dxb.truckmonitor.data.router.CoroutineDispatcherProvider
import com.dxb.truckmonitor.data.session.SessionContext
import com.dxb.truckmonitor.domain.helpers.TrucksObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SessionModule {

    @Provides
    fun provideCoroutineDispatcher() = CoroutineDispatcherProvider()

    @Singleton
    @Provides
    fun provideSessionContext(): SessionContext = SessionContext()

    @Singleton
    @Provides
    fun provideTrucksObserver(): TrucksObserver = TrucksObserver()
}