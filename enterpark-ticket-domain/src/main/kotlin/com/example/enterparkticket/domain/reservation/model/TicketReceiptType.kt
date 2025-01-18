package com.example.enterparkticket.domain.reservation.model

import com.example.enterparkticket.domain.common.entity.Money

enum class TicketReceiptType(val value: String, val price: Money) {
    ON_SITE("현장 수령", Money.ZERO),
    DELIVERY("배송", Money.DELIVERY_PRICE),
}
