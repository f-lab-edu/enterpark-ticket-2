package com.example.enterparkticket.domain.user.validator

import com.example.enterparkticket.domain.user.model.StateType
import com.example.enterparkticket.domain.user.model.User
import com.example.enterparkticket.domain.user.exception.ReRegisterUserException
import org.springframework.stereotype.Component

@Component
class UserValidator {

    fun validateState(user: User) {
        if (user.state == StateType.SUSPENDED) {
            throw ReRegisterUserException()
        }
    }
}