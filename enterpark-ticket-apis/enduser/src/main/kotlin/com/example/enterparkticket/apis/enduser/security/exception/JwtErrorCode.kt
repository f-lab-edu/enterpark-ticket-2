package com.example.enterparkticket.apis.enduser.security.exception

import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.UNAUTHORIZED

enum class JwtErrorCode(private val status: Int, private val message: String) {
    INVALID_TOKEN(UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(UNAUTHORIZED, "만료된 토큰입니다.");
}
