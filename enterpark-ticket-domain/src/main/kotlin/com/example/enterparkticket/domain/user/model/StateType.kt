package com.example.enterparkticket.domain.user.model

enum class StateType(val value: String) {
    NORMAL("일반"),
    DELETED("탈퇴"),
    SUSPENDED("정지"), // 탈퇴 후 7일 이내 재가입 불가 등
}
