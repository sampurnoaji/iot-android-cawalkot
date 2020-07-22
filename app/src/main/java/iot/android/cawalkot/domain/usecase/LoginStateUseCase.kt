package iot.android.cawalkot.domain.usecase

import iot.android.cawalkot.domain.repository.UserRepository
import javax.inject.Inject

class LoginStateUseCase @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(): Boolean = repository.isLoggedIn()
}
