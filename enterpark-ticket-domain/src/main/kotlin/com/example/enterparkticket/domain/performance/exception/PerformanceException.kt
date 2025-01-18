package com.example.enterparkticket.domain.performance.exception

sealed class PerformanceException(private val errorCode: PerformanceErrorCode) : RuntimeException()