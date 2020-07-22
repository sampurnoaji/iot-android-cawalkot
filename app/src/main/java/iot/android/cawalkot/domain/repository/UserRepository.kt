package iot.android.cawalkot.domain.repository

import iot.android.cawalkot.data.request.LoginRequest
import iot.android.cawalkot.domain.entity.LoginData
import iot.android.cawalkot.data.vo.LoadResult

interface UserRepository {
    suspend fun login(request: LoginRequest): LoadResult<LoginData>

    fun setLoggedIn(isLoggedIn: Boolean)
    fun saveUserSession(name: String, token: String)
    fun isLoggedIn(): Boolean
    fun getUsername(): String
    fun getToken(): String
}
