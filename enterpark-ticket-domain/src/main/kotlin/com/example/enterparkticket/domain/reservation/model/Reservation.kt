package com.example.enterparkticket.domain.reservation.model

import com.example.enterparkticket.domain.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class Reservation(
    userId: Long,
    performanceId: Long,
    seatId: Long?,
    ticketReceiptType: TicketReceiptType,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    var id: Long? = null
        protected set

    @Column(nullable = false)
    var userId: Long = userId
        protected set

    @Column(nullable = false)
    var performanceId: Long = performanceId
        protected set

    @Column(nullable = false)
    var seatId: Long? = seatId
        protected set

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var ticketReceipt: TicketReceiptType = ticketReceiptType
        protected set

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var state: StateType = StateType.WAITING_PAYMENT
        protected set
}