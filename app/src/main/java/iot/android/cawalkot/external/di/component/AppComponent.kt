package iot.android.cawalkot.external.di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import iot.android.cawalkot.MyApplication
import iot.android.cawalkot.external.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityInjectionModule::class,
        FragmentInjectionModule::class,
        ContextModule::class,
        NetworkModule::class,
        SharedPreferenceModule::class,
        CoroutineDispatcherModule::class,
        ViewModelModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun inject(instance: MyApplication?)
}
