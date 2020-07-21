package iot.android.cawalkot

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import iot.android.cawalkot.external.di.component.AppComponent
import iot.android.cawalkot.external.di.component.DaggerAppComponent
import iot.android.cawalkot.external.di.module.ContextModule
import iot.android.cawalkot.external.di.module.NetworkModule
import timber.log.Timber
import javax.inject.Inject

open class MyApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        initAppDependencyInjection()
    }

    private fun initAppDependencyInjection() {
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .networkModule(NetworkModule())
            .build()
        appComponent.inject(this)
    }
}
