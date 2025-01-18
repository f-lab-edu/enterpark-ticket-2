package com.example.enterparkticket.domain.common.exception

import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.INTERNAL_SERVER_ERROR

enum class GlobalErrorCode(private val status: Int, private val message: String) {

    SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 오류가 발생하였습니다."),
    NOT_AVAILABLE_DISTRIBUTED_LOCK(INTERNAL_SERVER_ERROR, "락 획득에 실패하였습니다.");
}
