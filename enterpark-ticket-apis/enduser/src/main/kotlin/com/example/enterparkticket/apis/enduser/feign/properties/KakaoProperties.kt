package com.example.enterparkticket.apis.enduser.feign.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("spring.security.oauth2.client.registration.kakao")
data class KakaoProperties(
    val clientId: String,
    val redirectUri: String,
    val clientSecret: String,
    val adminKey: String,
)
