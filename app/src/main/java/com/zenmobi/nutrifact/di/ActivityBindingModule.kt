package com.zenmobi.nutrifact.di

import com.zenmobi.nutrifact.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun providesMainActivity():MainActivity
}
