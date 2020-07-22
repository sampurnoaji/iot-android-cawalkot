package iot.android.cawalkot.data.service

import iot.android.cawalkot.data.request.LoginRequest
import iot.android.cawalkot.data.response.LoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginDto
}
