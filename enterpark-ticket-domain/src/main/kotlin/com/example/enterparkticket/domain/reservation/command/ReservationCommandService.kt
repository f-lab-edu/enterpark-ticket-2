package com.example.enterparkticket.domain.reservation.command

import com.example.enterparkticket.domain.reservation.model.Reservation
import com.example.enterparkticket.domain.reservation.model.repository.ReservationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ReservationCommandService(
    private val reservationRepository: ReservationRepository,
) {

    fun saveReservations(reservations: List<Reservation>) {
        reservationRepository.saveAll(reservations)
    }
}
