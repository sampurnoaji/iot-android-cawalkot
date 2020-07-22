package iot.android.cawalkot.external.di.module

import dagger.Binds
import dagger.Module
import iot.android.cawalkot.data.repository.UserRepositoryImpl
import iot.android.cawalkot.domain.repository.UserRepository

@Module
interface RepositoryModule {
    @Binds
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository
}
