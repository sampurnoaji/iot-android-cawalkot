package iot.android.cawalkot.external.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import iot.android.cawalkot.presentation.login.LoginFragment

@Module
abstract class FragmentInjectionModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
}
