package com.example.enterparkticket.apis.enduser.security.service

import com.example.enterparkticket.apis.enduser.security.dto.CustomOAuth2User
import com.example.enterparkticket.domain.user.command.UserCommandHandler
import com.example.enterparkticket.domain.user.port.OAuth2Port
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val oAuth2Port: OAuth2Port,
    private val userCommandHandler: UserCommandHandler,
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val attributes = super.loadUser(userRequest).attributes
        val oAuth2UserInfo = oAuth2Port.getOAuth2UserInfo(userRequest.accessToken.tokenValue)
        val user = userCommandHandler.loginUser(oAuth2UserInfo)
        return CustomOAuth2User(user, attributes)
    }
}
