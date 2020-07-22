package iot.android.cawalkot.data.mapper

import iot.android.cawalkot.data.response.LoginDto
import iot.android.cawalkot.domain.entity.LoginData
import iot.android.cawalkot.external.base.BaseMapper
import javax.inject.Inject

class LoginMapper @Inject constructor() : BaseMapper<LoginDto, LoginData>() {
    override fun map(input: LoginDto): LoginData {
        return LoginData(
            name = input.name ?: "",
            token = input.token ?: ""
        )
    }
}
