package com.example.enterparkticket.domain.seat.repository

import com.example.enterparkticket.domain.seat.model.Seat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface SeatRepository : JpaRepository<Seat, Long> {

    fun findByRemainingSeatPerformanceIdAndSeatNumberIn(
        performanceId: Long,
        seatNumbers: List<String>
    ): List<Seat>

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Seat s SET s.isReserved = true WHERE s.remainingSeat.performanceId = :performanceId AND s.seatNumber IN :seatNumbers")
    fun bulkUpdateReservedSeats(performanceId: Long, seatNumbers: List<String>)
}
