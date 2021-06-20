package com.zenmobi.nutrifact.base

import com.zenmobi.nutrifact.di.AppComponent
import com.zenmobi.nutrifact.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NutriFactApplication : DaggerApplication(){

    lateinit var appComponent:AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder().application(this).build()
        return appComponent
    }
}