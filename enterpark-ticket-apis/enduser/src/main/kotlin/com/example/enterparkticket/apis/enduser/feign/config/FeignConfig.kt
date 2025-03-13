package com.example.enterparkticket.apis.enduser.feign.config

import com.example.enterparkticket.apis.enduser.feign.client.BaseFeignClientsPackage
import feign.Logger
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackageClasses = [BaseFeignClientsPackage::class])
class FeignConfig {

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }
}
