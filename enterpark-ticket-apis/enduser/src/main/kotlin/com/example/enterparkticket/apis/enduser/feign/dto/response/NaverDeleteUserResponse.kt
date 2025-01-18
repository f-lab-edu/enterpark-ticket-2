package com.example.enterparkticket.apis.enduser.feign.dto.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(SnakeCaseStrategy::class)
data class NaverDeleteUserResponse(
    val accessToken: String,
    val result: String,
)
