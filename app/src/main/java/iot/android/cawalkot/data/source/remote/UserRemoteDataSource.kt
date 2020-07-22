package iot.android.cawalkot.data.source.remote

import iot.android.cawalkot.data.request.LoginRequest
import iot.android.cawalkot.data.response.LoginDto
import iot.android.cawalkot.data.service.ApiService
import iot.android.cawalkot.data.vo.LoadResult
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val service: ApiService) :
    RemoteDataSource() {
    suspend fun login(dispatcher: CoroutineDispatcher, request: LoginRequest): LoadResult<LoginDto> {
        return safeApiCall(dispatcher) { service.login(request) }
    }
}
