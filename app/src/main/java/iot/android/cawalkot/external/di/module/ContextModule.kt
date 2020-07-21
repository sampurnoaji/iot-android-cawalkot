package iot.android.cawalkot.external.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import iot.android.cawalkot.MyApplication
import javax.inject.Singleton

@Module
class ContextModule(val application: MyApplication) {
    @Provides
    @Singleton
    fun provideContext(): Context = application
}
