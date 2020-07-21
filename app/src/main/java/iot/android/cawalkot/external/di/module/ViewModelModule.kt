package iot.android.cawalkot.external.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import iot.android.cawalkot.external.di.factory.ViewModelFactory

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
