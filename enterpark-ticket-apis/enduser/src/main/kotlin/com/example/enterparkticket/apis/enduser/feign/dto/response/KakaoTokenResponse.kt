package com.example.enterparkticket.apis.enduser.feign.dto.response

import com.example.enterparkticket.application.usecase.user.dto.OAuth2AccessTokenDto
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = SnakeCaseStrategy::class)
data class KakaoTokenResponse(
    val tokenType: String,
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
    val refreshTokenExpiresIn: Int,
) {

    fun toOAuthTokenDto(): OAuth2AccessTokenDto {
        return OAuth2AccessTokenDto(accessToken, expiresIn)
    }
}
