package com.example.enterparkticket.domain.user.command

import com.example.enterparkticket.domain.user.command.dto.AccessTokenDto
import com.example.enterparkticket.domain.user.command.dto.UpdateUserAddressDto
import com.example.enterparkticket.domain.user.model.User
import com.example.enterparkticket.domain.user.port.OAuth2UserInfoDto
import com.example.enterparkticket.domain.user.query.UserQueryService
import com.example.enterparkticket.domain.user.validator.UserValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserCommandHandler(
    private val userCommandService: UserCommandService,
    private val userQueryService: UserQueryService,
    private val userValidator: UserValidator,
    private val userRedisCommandService: UserRedisCommandService,
) {

    fun loginUser(dto: OAuth2UserInfoDto): User {
        return userQueryService.findByOAuthInfo(dto.oAuthInfo)?.apply {
            userValidator.validateState(this)
            this.updateUser(dto.name, dto.email, dto.phoneNumber)
        } ?: userCommandService.saveUser(dto.toUserEntity())
    }

    fun withdrawUser(userId: Long): User {
        val user = userQueryService.findByUserId(userId)
        user.withdrawUser()
        return user
    }

    fun updateUserAddress(userId: Long, dto: UpdateUserAddressDto) {
        val user = userQueryService.findByUserId(userId)
        user.updateAddress(dto.address)
    }

    fun saveAccessToken(userId: Long?, dto: AccessTokenDto) {
        userRedisCommandService.saveToken(userId, dto)
    }

    fun deleteAccessToken(userId: Long) {
        userRedisCommandService.deleteToken(userId)
    }
}
