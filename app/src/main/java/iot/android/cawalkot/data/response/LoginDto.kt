package iot.android.cawalkot.data.response

import com.squareup.moshi.Json

data class LoginDto(
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "token")
    val token: String? = null
)
