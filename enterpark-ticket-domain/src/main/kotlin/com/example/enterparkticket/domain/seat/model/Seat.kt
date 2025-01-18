package com.example.enterparkticket.domain.seat.model

import com.example.enterparkticket.domain.common.entity.BaseEntity
import com.example.enterparkticket.domain.remainingseat.model.RemainingSeat
import jakarta.persistence.*

@Entity
class Seat(
    seatNumber: String,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    var id: Long? = null
        protected set

    @Column(nullable = false, length = 15)
    var seatNumber: String = seatNumber
        protected set

    @Column(nullable = false)
    var isReserved: Boolean = false
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "remaining_seat_id")
    var remainingSeat: RemainingSeat? = null
        protected set
}
