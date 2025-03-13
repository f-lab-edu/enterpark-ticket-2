package com.example.enterparkticket.domain.user.query

import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.TOKEN_PREFIX
import com.example.enterparkticket.domain.user.exception.NotFoundTokenException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class UserRedisQueryService(private val redisTemplate: RedisTemplate<String, String>) {

    fun getToken(userId: Long): String {
        val key = TOKEN_PREFIX + userId
        val value = redisTemplate.opsForValue()[key]
        return if (!value.isNullOrEmpty()) {
            value
        } else {
            throw NotFoundTokenException()
        }
    }
}
