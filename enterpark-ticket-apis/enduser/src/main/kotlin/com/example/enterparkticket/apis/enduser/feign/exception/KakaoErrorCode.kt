package com.example.enterparkticket.apis.enduser.feign.exception

import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.UNAUTHORIZED

enum class KakaoErrorCode(private val status: Int, private val message: String) {
    INVALID_EMAIL(UNAUTHORIZED, "유효하지 않은 이메일 주소입니다."),
}
