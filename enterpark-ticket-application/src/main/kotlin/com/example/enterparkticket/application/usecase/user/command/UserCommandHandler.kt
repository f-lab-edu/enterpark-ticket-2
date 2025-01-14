package com.example.enterparkticket.application.usecase.user.command

import com.example.enterparkticket.application.usecase.user.dto.OAuth2UserInfoDto
import com.example.enterparkticket.application.usecase.user.dto.UpdateUserDto
import com.example.enterparkticket.application.usecase.user.port.OAuth2Port
import com.example.enterparkticket.domain.user.command.UserCommandService
import com.example.enterparkticket.domain.user.model.User
import com.example.enterparkticket.domain.user.query.UserQueryService
import com.example.enterparkticket.domain.user.validator.UserValidator
import org.springframework.stereotype.Service

@Service
class UserCommandHandler(
    private val oAuth2Port: OAuth2Port,
    private val userCommandService: UserCommandService,
    private val userQueryService: UserQueryService,
    private val userValidator: UserValidator,
) {

    fun loginUser(dto: OAuth2UserInfoDto): User {
        return userQueryService.findByOAuthInfo(dto.oAuthInfo)?.apply {
            userValidator.validateState(this)
            userCommandService.updateUser(this, dto.toUpdateUserDto())
        } ?: userCommandService.saveUser(dto.toUserEntity())
    }

    fun withdrawUser(userId: Long) {
        val user = userQueryService.findByUserId(userId)
        val oid = userCommandService.withdrawUser(user)
        oAuth2Port.unlinkUser(oid)
    }

    fun updateUserAddress(userId: Long, dto: UpdateUserDto) {
        val user = userQueryService.findByUserId(userId)
        userCommandService.updateUserAddress(user, dto.address)
    }
}
