package com.example.enterparkticket.domain.seat.exception

class AlreadyReservedSeatException :
    ReservationException(ReservationErrorCode.SEAT_ALREADY_RESERVED)
