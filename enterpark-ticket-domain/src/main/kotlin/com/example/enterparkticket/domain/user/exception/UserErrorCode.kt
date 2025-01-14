package com.example.enterparkticket.domain.user.exception

import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.NOT_FOUND
import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.UNAUTHORIZED

enum class UserErrorCode(private val status: Int, private val message: String) {

    USER_NOT_FOUND(NOT_FOUND, "존재하지 않는 회원입니다."),
    USER_RE_REGISTER(UNAUTHORIZED, "탈퇴 후 7일 이내에는 재가입이 불가능합니다."),
}
