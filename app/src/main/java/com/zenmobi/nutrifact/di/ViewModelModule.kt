package com.zenmobi.nutrifact.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zenmobi.nutrifact.MainViewModel
import com.zenmobi.nutrifact.common.ViewModelFactory
import com.zenmobi.nutrifact.common.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun onDeepLinkDispatcherViewModel(viewModel: MainViewModel): ViewModel
}