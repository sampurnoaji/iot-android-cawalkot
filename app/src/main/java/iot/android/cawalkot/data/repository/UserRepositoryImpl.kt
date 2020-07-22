package iot.android.cawalkot.data.repository

import iot.android.cawalkot.data.dispatcher.DispatcherProvider
import iot.android.cawalkot.data.mapper.LoginMapper
import iot.android.cawalkot.data.request.LoginRequest
import iot.android.cawalkot.data.source.local.UserLocalDataSource
import iot.android.cawalkot.data.source.remote.UserRemoteDataSource
import iot.android.cawalkot.data.vo.LoadResult
import iot.android.cawalkot.domain.entity.LoginData
import iot.android.cawalkot.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val loginMapper: LoginMapper,
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {
    override suspend fun login(request: LoginRequest): LoadResult<LoginData> {
        return when (val apiResult = remoteDataSource.login(dispatcher.io, request)) {
            is LoadResult.Success -> LoadResult.Success(loginMapper.map(apiResult.data))
            is LoadResult.Error -> LoadResult.Error(apiResult.cause, apiResult.code, apiResult.errorMessage)
            else -> LoadResult.Error()
        }
    }

    override fun setLoggedIn(isLoggedIn: Boolean) = localDataSource.setLoggedIn(isLoggedIn)
    override fun saveUserSession(name: String, token: String) = localDataSource.saveUserSession(name, token)
    override fun isLoggedIn(): Boolean = localDataSource.isLoggedIn()
    override fun getUsername(): String = localDataSource.getUsername()
    override fun getToken(): String = localDataSource.getToken()
}
