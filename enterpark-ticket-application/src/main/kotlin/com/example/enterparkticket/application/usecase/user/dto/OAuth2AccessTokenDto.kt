package com.example.enterparkticket.application.usecase.user.dto

data class OAuth2AccessTokenDto(
    val accessToken: String,
    val expiresIn: Int,
)
