package com.example.enterparkticket.domain.remainingseat.model

import com.example.enterparkticket.domain.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class RemainingSeat(
    grade: GradeType,
    price: Int,
    count: Int,
    performanceId: Long,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "remaining_seat_id")
    var id: Long? = null
        protected set

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var grade: GradeType = grade
        protected set

    @Column(nullable = false)
    var price: Int = price
        protected set

    @Column(nullable = false)
    var count: Int = count
        protected set

    @Column(nullable = false)
    var performanceId: Long = performanceId
        protected set

    fun decreaseCount() {
        if (count > 0) {
            count -= 1
        }
    }
}
