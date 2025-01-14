package com.example.enterparkticket.domain.user.command

data class UpdateUserDto(
    val name: String,
    val email: String,
    val phoneNumber: String,
)
