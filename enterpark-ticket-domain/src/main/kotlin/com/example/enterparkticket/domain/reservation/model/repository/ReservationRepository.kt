package com.example.enterparkticket.domain.reservation.model.repository

import com.example.enterparkticket.domain.reservation.model.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository : JpaRepository<Reservation, Long> {
}
