package com.example.enterparkticket.apis.enduser.feign.adapter

import com.example.enterparkticket.apis.enduser.feign.client.KakaoUserClient
import com.example.enterparkticket.apis.enduser.feign.properties.KakaoProperties
import com.example.enterparkticket.application.usecase.user.dto.OAuth2UserInfoDto
import com.example.enterparkticket.application.usecase.user.port.OAuth2Port
import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.BEARER
import com.example.enterparkticket.domain.common.consts.EnterparkTicketConsts.KAKAO_AK
import org.springframework.stereotype.Component

@Component
class KakaoFeignAdapter(
    private val kakaoProperties: KakaoProperties,
    private val kakaoUserClient: KakaoUserClient,
) : OAuth2Port {

    override fun getOAuth2UserInfo(accessToken: String): OAuth2UserInfoDto {
        val userInfo = kakaoUserClient.getUserInfo(BEARER + accessToken)
        userInfo.validateEmail()
        return userInfo.toOAuth2UserInfoDto()
    }

    override fun unlinkUser(oAuthId: Long) {
        kakaoUserClient.unlinkUser(KAKAO_AK + kakaoProperties.adminKey, oAuthId)
    }
}
