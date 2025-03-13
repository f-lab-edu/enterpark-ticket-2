package com.example.enterparkticket.apis.enduser.feign.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("spring.security.oauth2.client.registration.naver")
data class NaverProperties(
    val clientId: String,
    val clientSecret: String,
)
