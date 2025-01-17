package com.example.enterparkticket.apis.enduser.feign.exception

sealed class KakaoException(private val errorCode: KakaoErrorCode) : RuntimeException()