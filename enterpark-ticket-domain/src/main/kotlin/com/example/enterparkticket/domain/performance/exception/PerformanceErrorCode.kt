package com.example.enterparkticket.domain.performance.exception

import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.BAD_REQUEST
import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.NOT_FOUND

enum class PerformanceErrorCode(private val status: Int, private val message: String) {

    PERFORMANCE_NOT_FOUND(NOT_FOUND, "존재하지 않는 공연입니다."),
    AGE_LIMIT(BAD_REQUEST, "공연 관람 가능 나이가 아닙니다."),
}
