package com.example.enterparkticket.apis.enduser.security

import com.example.enterparkticket.apis.enduser.security.dto.UserPrincipal
import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.AUTHORIZATION_HEADER
import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.BEARER
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtTokenFilter(
    private val jwtTokenProvider: JwtTokenProvider,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolveToken(request)
        if (token != null) {
            val authentication = getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val header = request.getHeader(AUTHORIZATION_HEADER)
        if (header != null && header.startsWith(BEARER)) {
            return header.substring(BEARER.length)
        }
        return null
    }

    private fun getAuthentication(token: String): Authentication {
        val accessTokenInfo = jwtTokenProvider.parseAccessToken(token)
        val principal = UserPrincipal(accessTokenInfo.userId, accessTokenInfo.authorities)
        return UsernamePasswordAuthenticationToken(principal, null, principal.authorities)
    }
}
