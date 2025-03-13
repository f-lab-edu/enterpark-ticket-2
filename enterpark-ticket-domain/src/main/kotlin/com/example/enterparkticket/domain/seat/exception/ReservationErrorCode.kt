package com.example.enterparkticket.domain.seat.exception

import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.CONFLICT

enum class ReservationErrorCode(private val status: Int, private val message: String) {

    SEAT_ALREADY_RESERVED(CONFLICT, "이미 예매된 좌석입니다."),
}
