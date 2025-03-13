package com.example.enterparkticket.domain.user.adapter

import com.example.enterparkticket.domain.reservation.port.UserPort
import com.example.enterparkticket.domain.user.query.UserQueryService
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class UserPortAdapter(
    private val userQueryService: UserQueryService,
) : UserPort {

    override fun getUserAge(userId: Long): LocalDate {
        return userQueryService.findByUserId(userId).birthDate
    }
}
