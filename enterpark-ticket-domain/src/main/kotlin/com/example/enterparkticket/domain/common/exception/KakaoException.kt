package com.example.enterparkticket.domain.common.exception

sealed class KakaoException(private val errorCode: KakaoErrorCode) : RuntimeException()