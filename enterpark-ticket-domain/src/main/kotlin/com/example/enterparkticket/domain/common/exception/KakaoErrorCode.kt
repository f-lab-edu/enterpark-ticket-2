package com.example.enterparkticket.domain.common.exception

import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.NOT_FOUND
import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.UNAUTHORIZED

enum class KakaoErrorCode(private val status: Int, private val message: String) {
    INVALID_EMAIL(UNAUTHORIZED, "유효하지 않은 이메일 주소입니다."),
    KAKAO_TOKEN_NOT_FOUND(NOT_FOUND, "해당 회원의 카카오 토큰이 존재하지 않습니다.");
}
