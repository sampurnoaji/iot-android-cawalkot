package iot.android.cawalkot.external.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import iot.android.cawalkot.MainActivity

@Module
abstract class ActivityInjectionModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
