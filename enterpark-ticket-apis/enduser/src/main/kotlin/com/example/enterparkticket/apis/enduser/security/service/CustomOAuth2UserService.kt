package com.example.enterparkticket.apis.enduser.security.service

import com.example.enterparkticket.apis.enduser.security.dto.CustomOAuth2User
import com.example.enterparkticket.domain.user.command.UserCommandHandler
import com.example.enterparkticket.domain.user.command.dto.AccessTokenDto
import com.example.enterparkticket.domain.user.model.User
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val userCommandHandler: UserCommandHandler,
    private val oAuth2Service: OAuth2Service,
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val attributes = super.loadUser(userRequest).attributes
        val user = loginUser(userRequest)
        saveAccessToken(user, userRequest)
        return CustomOAuth2User(user, attributes)
    }

    private fun loginUser(userRequest: OAuth2UserRequest): User {
        val registrationId = userRequest.clientRegistration.registrationId
        val accessToken = userRequest.accessToken.tokenValue
        val oAuth2UserInfo = oAuth2Service.getOAuth2UserInfo(registrationId, accessToken)
        return userCommandHandler.loginUser(oAuth2UserInfo)
    }

    private fun saveAccessToken(user: User, userRequest: OAuth2UserRequest) {
        val accessToken = userRequest.accessToken.tokenValue
        val expiresIn = userRequest.accessToken.expiresAt?.epochSecond
        val dto = AccessTokenDto(accessToken, expiresIn)
        userCommandHandler.saveAccessToken(user.id, dto)
    }
}
