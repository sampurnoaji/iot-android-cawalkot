package iot.android.cawalkot.external.di.module

import dagger.Binds
import dagger.Module
import iot.android.cawalkot.data.dispatcher.CoroutineDispatcherProvider
import iot.android.cawalkot.data.dispatcher.DispatcherProvider

@Module
interface CoroutineDispatcherModule {
    @Binds
    fun bindDispatcher(dispatcherProvider: CoroutineDispatcherProvider): DispatcherProvider
}
