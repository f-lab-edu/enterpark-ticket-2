package com.example.enterparkticket.domain.common.exception

sealed class GlobalException(private val errorCode: GlobalErrorCode) : RuntimeException()
