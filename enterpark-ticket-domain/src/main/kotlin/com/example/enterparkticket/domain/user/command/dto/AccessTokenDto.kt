package com.example.enterparkticket.domain.user.command.dto

data class AccessTokenDto(
    val accessToken: String,
    val expiresIn: Long?,
)
