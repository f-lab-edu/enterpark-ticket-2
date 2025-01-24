package com.example.enterparkticket.domain.user.model.repository

import com.example.enterparkticket.domain.user.model.OAuthInfo
import com.example.enterparkticket.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByOAuthInfo(oAuthInfo: OAuthInfo): User?
}
