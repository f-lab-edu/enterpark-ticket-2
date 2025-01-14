package com.example.enterparkticket.apis.enduser.security.exception

sealed class JwtException(private val errorCode: JwtErrorCode) : RuntimeException()
