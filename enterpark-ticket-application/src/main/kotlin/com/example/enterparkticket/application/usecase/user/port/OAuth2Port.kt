package com.example.enterparkticket.application.usecase.user.port

import com.example.enterparkticket.application.usecase.user.dto.OAuth2UserInfoDto

interface OAuth2Port {

    fun getOAuth2UserInfo(accessToken: String): OAuth2UserInfoDto

    fun unlinkUser(oAuthId: Long)
}
