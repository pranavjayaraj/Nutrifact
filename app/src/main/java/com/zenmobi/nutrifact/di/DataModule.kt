package com.zenmobi.nutrifact.di

import com.zenmobi.data.ApiRepositoryImpl
import com.zenmobi.data.NetworkModule
import com.zenmobi.data.SchedulersFacade
import com.zenmobi.domain.SchedulerProvider
import com.zenmobi.domain.repository.ApiRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideApiRepository(apiRepositoryImpl: ApiRepositoryImpl): ApiRepository

    @Binds
    @Singleton
    abstract fun provideSchedulerProvider(schedulersFacade: SchedulersFacade): SchedulerProvider

}