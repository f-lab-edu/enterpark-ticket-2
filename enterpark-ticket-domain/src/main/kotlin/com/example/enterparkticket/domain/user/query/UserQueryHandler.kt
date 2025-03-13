package com.example.enterparkticket.domain.user.query

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserQueryHandler(
    private val userRedisQueryService: UserRedisQueryService,
) {

    fun getAccessToken(userId: Long): String {
        return userRedisQueryService.getToken(userId)
    }
}
