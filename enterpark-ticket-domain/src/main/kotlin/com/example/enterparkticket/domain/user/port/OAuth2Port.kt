package com.example.enterparkticket.domain.user.port

interface OAuth2Port {

    fun getOAuth2UserInfo(accessToken: String): OAuth2UserInfoDto

    fun withdrawUser(id: Long)
}
