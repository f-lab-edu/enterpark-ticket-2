package com.example.enterparkticket.apis.enduser.security.dto

import com.example.enterparkticket.domain.user.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User

class CustomOAuth2User(
    private val user: User,
    private val attributes: MutableMap<String, Any>,
) : OAuth2User, UserDetails {

    override fun getName(): String {
        return user.id.toString()
    }

    override fun getAttributes(): MutableMap<String, Any> {
        return attributes
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<SimpleGrantedAuthority>()
        authorities.add(SimpleGrantedAuthority("ROLE_${user.role}"))
        return authorities
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return user.id.toString()
    }
}
