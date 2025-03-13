package com.example.enterparkticket.apis.enduser.security.dto

import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.TOKEN_ROLE
import io.jsonwebtoken.Claims
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class AccessTokenInfo(
    val userId: Long,
    val authorities: List<SimpleGrantedAuthority>,
) {

    companion object {

        fun of(claims: Claims): AccessTokenInfo {
            return AccessTokenInfo(
                claims.subject.toLong(),
                listOf(SimpleGrantedAuthority(claims[TOKEN_ROLE].toString())),
            )
        }
    }
}