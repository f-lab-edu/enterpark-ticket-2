package com.example.enterparkticket.domain.user.command

import com.example.enterparkticket.domain.user.model.User
import com.example.enterparkticket.domain.user.model.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserCommandService(
    private val userRepository: UserRepository,
) {

    fun saveUser(user: User): User {
        return userRepository.save(user)
    }
}
