package com.example.enterparkticket.domain.reservation.command

import com.example.enterparkticket.domain.common.aop.lock
import com.example.enterparkticket.domain.reservation.command.dto.CreateReservationDto
import com.example.enterparkticket.domain.reservation.event.ReservationWaitingPaymentEvent
import com.example.enterparkticket.domain.reservation.model.Reservation
import com.example.enterparkticket.domain.reservation.port.PerformancePort
import com.example.enterparkticket.domain.reservation.port.RemainingSeatPort
import com.example.enterparkticket.domain.reservation.port.SeatPort
import com.example.enterparkticket.domain.reservation.port.UserPort
import com.example.enterparkticket.domain.seat.model.Seat
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class ReservationCommandHandler(
    private val userPort: UserPort,
    private val performancePort: PerformancePort,
    private val seatPort: SeatPort,
    private val remainingSeatPort: RemainingSeatPort,
    private val reservationCommandService: ReservationCommandService,
    private val publisher: ApplicationEventPublisher,
) {

    fun createReservation(userId: Long, dto: CreateReservationDto) {
        checkUserAgeForPerformance(userId, dto.performanceId)
        val seats = seatPort.getNotReservedSeats(dto.performanceId, dto.toSeatNumbers())

        processReservationWithLock(dto)

        val reservations = saveReservations(userId, dto, seats)
        publisher.publishEvent(ReservationWaitingPaymentEvent(reservations))
    }

    private fun checkUserAgeForPerformance(userId: Long, performanceId: Long) {
        val userAge = userPort.getUserAge(userId)
        performancePort.checkUserAgeForPerformance(performanceId, userAge)
    }

    private fun processReservationWithLock(dto: CreateReservationDto) {
        lock(SEAT_LOCK_KEY, "${dto.performanceId}", dto.toSeatNumbers().joinToString(SEPARATOR)) {
            seatPort.updateReservedSeats(dto.performanceId, dto.toSeatNumbers())
            dto.seats.forEach {
                remainingSeatPort.decreaseSeatCount(dto.performanceId, it.gradeType)
            }
        }
    }

    private fun saveReservations(
        userId: Long,
        dto: CreateReservationDto,
        seats: List<Seat>
    ): List<Reservation> {
        val reservations = seats.map { dto.toReservationEntity(userId, it.id) }
        reservationCommandService.saveReservations(reservations)
        return reservations
    }

    companion object {
        const val SEAT_LOCK_KEY = "SEAT_LOCK"
        const val SEPARATOR = ","
    }
}
