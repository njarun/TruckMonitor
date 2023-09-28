package com.dxb.truckmonitor.di.module

import com.dxb.truckmonitor.data.router.repository.TrucksRepositoryImpl
import com.dxb.truckmonitor.domain.router.repository.TrucksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class, SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTrucksRepository(trucksRepositoryImpl: TrucksRepositoryImpl): TrucksRepository
}