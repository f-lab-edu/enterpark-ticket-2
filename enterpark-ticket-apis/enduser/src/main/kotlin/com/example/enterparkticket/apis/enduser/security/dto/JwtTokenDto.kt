package com.example.enterparkticket.apis.enduser.security.dto

data class JwtTokenDto(
    val accessToken: String,
    val refreshToken: String,
) {

    companion object {

        fun of(accessToken: String, refreshToken: String): JwtTokenDto {
            return JwtTokenDto(accessToken, refreshToken)
        }
    }
}
