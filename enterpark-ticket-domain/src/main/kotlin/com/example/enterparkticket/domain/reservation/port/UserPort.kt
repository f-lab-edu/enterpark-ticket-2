package com.example.enterparkticket.domain.reservation.port

import java.time.LocalDate

interface UserPort {

    fun getUserAge(userId: Long): LocalDate
}
