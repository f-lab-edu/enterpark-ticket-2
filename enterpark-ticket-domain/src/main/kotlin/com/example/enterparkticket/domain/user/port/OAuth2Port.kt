package com.example.enterparkticket.domain.user.port

import com.example.enterparkticket.domain.user.command.dto.OAuth2UserInfoDto

interface OAuth2Port {

    fun getOAuth2UserInfo(accessToken: String): OAuth2UserInfoDto

    fun unlinkUser(oAuthId: Long)
}
