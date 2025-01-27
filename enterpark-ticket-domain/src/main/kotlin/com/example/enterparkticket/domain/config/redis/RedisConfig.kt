package com.example.enterparkticket.domain.config.redis

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory

@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}")
    private val host: String,
    @Value("\${spring.data.redis.port}")
    private val port: Int,
    @Value("\${spring.data.redis.password:}")
    private val password: String,
) {

    @Bean
    fun customRedisConnectionFactory(): RedisConnectionFactory {
        val config = RedisStandaloneConfiguration(host, port).apply {
            val configPassword = this@RedisConfig.password
            if (configPassword.isNotBlank()) {
                password = RedisPassword.of(configPassword)
            }
        }
        return LettuceConnectionFactory(config)
    }

    @Bean
    fun redissonClient(): RedissonClient {
        val config = Config().apply {
            useSingleServer().apply {
                address = "$REDISSON_HOST_PREFIX$host:$port"
                val configPassword = this@RedisConfig.password
                if (configPassword.isNotBlank()) {
                    password = configPassword
                }
            }
        }
        return Redisson.create(config)
    }

    companion object {
        const val REDISSON_HOST_PREFIX = "redis://"
    }
}
