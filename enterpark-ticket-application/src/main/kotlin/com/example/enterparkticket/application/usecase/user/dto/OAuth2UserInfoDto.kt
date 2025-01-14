package com.example.enterparkticket.application.usecase.user.dto

import com.example.enterparkticket.domain.user.command.UpdateUserDto
import com.example.enterparkticket.domain.user.model.GenderType
import com.example.enterparkticket.domain.user.model.OAuthInfo
import com.example.enterparkticket.domain.user.model.User
import java.time.LocalDate

data class OAuth2UserInfoDto(
    val oAuthInfo: OAuthInfo,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val birthDate: LocalDate,
    val gender: GenderType,
) {

    fun toUserEntity(): User {
        return User(oAuthInfo, name, email, phoneNumber, birthDate, gender)
    }

    fun toUpdateUserDto(): UpdateUserDto {
        return UpdateUserDto(name, email, phoneNumber)
    }
}
