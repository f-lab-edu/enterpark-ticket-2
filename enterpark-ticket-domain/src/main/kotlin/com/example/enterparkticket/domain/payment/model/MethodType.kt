package com.example.enterparkticket.domain.payment.model

enum class MethodType(val value: String) {
    CARD("카드 결제"),
    MOBILE("핸드폰 결제"),
    DEPOSIT("무통장 입금"),
}
