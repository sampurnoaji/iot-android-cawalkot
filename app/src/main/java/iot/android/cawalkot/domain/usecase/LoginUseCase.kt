package iot.android.cawalkot.domain.usecase

import iot.android.cawalkot.data.request.LoginRequest
import iot.android.cawalkot.data.vo.LoadResult
import iot.android.cawalkot.domain.entity.LoginParams
import iot.android.cawalkot.domain.repository.UserRepository
import iot.android.cawalkot.external.base.BaseUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(val repository: UserRepository): BaseUseCase<LoginParams, LoadResult<Boolean>>() {
    override suspend fun invoke(params: LoginParams): LoadResult<Boolean> {
        val result = repository.login(LoginRequest(params.username, params.password))
        if (result is LoadResult.Success) {
            handleLoginSuccess(result.data.name, result.data.token)
        }
        return when (result) {
            is LoadResult.Success -> LoadResult.Success(true)
            is LoadResult.Error -> LoadResult.Error(result.cause, result.code, result.errorMessage)
            else -> LoadResult.Error()
        }
    }

    private fun handleLoginSuccess(name: String, token: String) {
        repository.setLoggedIn(true)
        repository.saveUserSession(name, token)
    }
}
