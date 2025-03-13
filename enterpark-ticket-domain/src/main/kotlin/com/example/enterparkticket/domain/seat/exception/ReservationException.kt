package com.example.enterparkticket.domain.seat.exception

sealed class ReservationException(private val errorCode: ReservationErrorCode) : RuntimeException()
