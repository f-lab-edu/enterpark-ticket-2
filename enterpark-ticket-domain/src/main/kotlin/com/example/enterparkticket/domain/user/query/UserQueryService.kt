package com.example.enterparkticket.domain.user.query

import com.example.enterparkticket.domain.user.model.OAuthInfo
import com.example.enterparkticket.domain.user.model.User
import com.example.enterparkticket.domain.user.exception.NotFoundUserException
import com.example.enterparkticket.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserQueryService(private val userRepository: UserRepository) {

    fun findByUserId(userId: Long): User {
        return userRepository.findById(userId).orElseThrow {
            NotFoundUserException()
        }
    }

    fun findByOAuthInfo(oAuthInfo: OAuthInfo): User? {
        return userRepository.findByOAuthInfo(oAuthInfo)
    }
}
