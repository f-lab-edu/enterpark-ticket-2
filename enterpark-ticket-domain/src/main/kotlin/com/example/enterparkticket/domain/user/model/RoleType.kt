package com.example.enterparkticket.domain.user.model

enum class RoleType(val value: String) {
    USER("일반 회원"),
    MANAGER("티켓 매니저"),
    ADMIN("관리자"),
}
