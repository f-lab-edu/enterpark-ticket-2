package com.example.enterparkticket.domain.user.exception

sealed class UserException(private val errorCode: UserErrorCode) : RuntimeException()