package com.example.enterparkticket.domain.user.command

import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.TOKEN_PREFIX
import com.example.enterparkticket.domain.user.command.dto.AccessTokenDto
import com.example.enterparkticket.domain.user.exception.DeletedTokenException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class UserRedisCommandService(private val redisTemplate: RedisTemplate<String, String>) {

    fun saveToken(userId: Long?, dto: AccessTokenDto) {
        val key = TOKEN_PREFIX + userId
        val timeout = dto.expiresIn?.times(1000L) ?: 0L
        redisTemplate.opsForValue()
            .set(key, dto.accessToken, timeout, TimeUnit.MILLISECONDS)
    }

    fun deleteToken(userId: Long) {
        val key = TOKEN_PREFIX + userId
        if (redisTemplate.hasKey(key)) {
            val isDeleted = redisTemplate.delete(key)
            if (!isDeleted) {
                throw DeletedTokenException()
            }
        }
    }
}
