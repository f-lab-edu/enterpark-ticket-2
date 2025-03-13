package com.example.enterparkticket.apis.enduser.security.dto

import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
    val userId: Long,
    val authorities: List<SimpleGrantedAuthority>,
)
