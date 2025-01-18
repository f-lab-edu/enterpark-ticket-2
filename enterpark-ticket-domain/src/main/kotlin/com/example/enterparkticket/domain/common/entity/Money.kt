package com.example.enterparkticket.domain.common.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
class Money(amount: BigDecimal) {

    @Column(nullable = false, precision = 21, scale = 6)
    var amount: BigDecimal = amount
        protected set

    companion object {

        val ZERO = wons(0)
        val DELIVERY_PRICE = wons(3200)

        fun wons(amount: Long): Money {
            return Money(BigDecimal.valueOf(amount))
        }

        fun wons(amount: Double): Money {
            return Money(BigDecimal.valueOf(amount))
        }
    }
}
