package com.example.enterparkticket.apis.enduser.feign.dto.response

import com.example.enterparkticket.domain.user.port.OAuth2UserInfoDto
import com.example.enterparkticket.apis.enduser.feign.exception.InvalidEmailException
import com.example.enterparkticket.domain.user.model.GenderType
import com.example.enterparkticket.domain.user.model.OAuthInfo
import com.example.enterparkticket.domain.user.model.OAuthProvider
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDate

@JsonNaming(SnakeCaseStrategy::class)
data class KakaoUserInfoResponse(
    val id: Long,
    val kakaoAccount: KakaoAccount,
) {

    fun validateEmail() {
        if (!kakaoAccount.isEmailValid || !kakaoAccount.isEmailVerified) {
            throw InvalidEmailException()
        }
    }

    fun toOAuth2UserInfoDto(): OAuth2UserInfoDto {
        val year = kakaoAccount.birthyear.toInt()
        val month = kakaoAccount.birthday.substring(0, 2).toInt()
        val dayOfMonth = kakaoAccount.birthday.substring(2, 4).toInt()
        return OAuth2UserInfoDto(
            OAuthInfo(OAuthProvider.KAKAO, id.toString()),
            kakaoAccount.name,
            kakaoAccount.email,
            kakaoAccount.phoneNumber,
            LocalDate.of(year, month, dayOfMonth),
            GenderType.valueOf(kakaoAccount.gender.uppercase()),
        )
    }
}

@JsonNaming(SnakeCaseStrategy::class)
data class KakaoAccount(
    val isEmailValid: Boolean,
    val isEmailVerified: Boolean,
    val email: String,
    val name: String,
    val gender: String,
    val birthyear: String,
    val birthday: String,
    val phoneNumber: String,
)
