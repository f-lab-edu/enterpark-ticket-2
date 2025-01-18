package com.example.enterparkticket.apis.enduser.feign.dto.response

import com.example.enterparkticket.domain.user.port.OAuth2UserInfoDto
import com.example.enterparkticket.domain.user.model.GenderType
import com.example.enterparkticket.domain.user.model.OAuthInfo
import com.example.enterparkticket.domain.user.model.OAuthProvider
import java.time.LocalDate

data class NaverUserInfoResponse(
    val resultcode: String,
    val message: String,
    val response: UserInfoResponse,
) {

    fun toOAuth2UserInfoDto(): OAuth2UserInfoDto {
        val year = response.birthyear.toInt()
        val birthday = response.birthday.split("-")
        val month = birthday[0].toInt()
        val dayOfMonth = birthday[1].toInt()
        return OAuth2UserInfoDto(
            OAuthInfo(OAuthProvider.NAVER, response.id),
            response.name,
            response.email,
            response.mobile,
            LocalDate.of(year, month, dayOfMonth),
            if (response.gender == GENDER_FEMALE) GenderType.FEMALE else GenderType.MALE,
        )
    }

    companion object {
        const val GENDER_FEMALE = "F"
    }
}

data class UserInfoResponse(
    val id: String,
    val email: String,
    val name: String,
    val gender: String,
    val birthyear: String,
    val birthday: String,
    val mobile: String,
)
