package com.example.enterparkticket.apis.enduser.feign.adapter

import com.example.enterparkticket.apis.enduser.feign.client.NaverTokenClient
import com.example.enterparkticket.apis.enduser.feign.client.NaverUserClient
import com.example.enterparkticket.apis.enduser.feign.properties.NaverProperties
import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.BEARER
import com.example.enterparkticket.domain.user.command.UserCommandHandler
import com.example.enterparkticket.domain.user.port.OAuth2Port
import com.example.enterparkticket.domain.user.port.OAuth2UserInfoDto
import com.example.enterparkticket.domain.user.query.UserQueryHandler
import org.springframework.stereotype.Component

@Component
class NaverOAuth2Adapter(
    private val naverProperties: NaverProperties,
    private val naverUserClient: NaverUserClient,
    private val naverTokenClient: NaverTokenClient,
    private val userQueryHandler: UserQueryHandler,
    private val userCommandHandler: UserCommandHandler,
) : OAuth2Port {

    override fun getOAuth2UserInfo(accessToken: String): OAuth2UserInfoDto {
        val userInfo = naverUserClient.getUserInfo(BEARER + accessToken)
        return userInfo.toOAuth2UserInfoDto()
    }

    override fun withdrawUser(id: Long) {
        val accessToken = userQueryHandler.getAccessToken(id)
        naverTokenClient.deleteUser(
            naverProperties.clientId,
            naverProperties.clientSecret,
            accessToken
        )
        userCommandHandler.deleteAccessToken(id)
    }
}
