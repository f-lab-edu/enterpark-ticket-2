package com.example.enterparkticket.apis.enduser.security.handler

import com.example.enterparkticket.apis.enduser.security.JwtTokenProvider
import com.example.enterparkticket.apis.enduser.security.dto.CustomOAuth2User
import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.ROLE_PREFIX
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class OAuth2AuthenticationSuccessHandler(
    private val jwtTokenProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper,
) : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val principal = authentication.principal as CustomOAuth2User
        val jwtToken = jwtTokenProvider.createToken(
            principal.username,
            principal.authorities.first().authority
        )

        response.contentType = "application/json"
        response.writer.write(objectMapper.writeValueAsString(jwtToken))
    }
}
