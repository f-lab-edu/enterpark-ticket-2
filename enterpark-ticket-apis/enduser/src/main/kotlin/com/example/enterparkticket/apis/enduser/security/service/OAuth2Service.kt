package com.example.enterparkticket.apis.enduser.security.service

import com.example.enterparkticket.apis.enduser.feign.adapter.KakaoOAuth2Adapter
import com.example.enterparkticket.apis.enduser.feign.adapter.NaverOAuth2Adapter
import com.example.enterparkticket.domain.user.command.UserCommandHandler
import com.example.enterparkticket.domain.user.model.OAuthProvider
import com.example.enterparkticket.domain.user.model.OAuthProvider.KAKAO
import com.example.enterparkticket.domain.user.model.OAuthProvider.NAVER
import com.example.enterparkticket.domain.user.port.OAuth2Port
import com.example.enterparkticket.domain.user.port.OAuth2UserInfoDto
import org.springframework.stereotype.Service

@Service
class OAuth2Service(
    private val kakaoOAuth2Adapter: KakaoOAuth2Adapter,
    private val naverOAuth2Adapter: NaverOAuth2Adapter,
    private val userCommandHandler: UserCommandHandler,
) {

    fun getOAuth2UserInfo(registrationId: String, accessToken: String): OAuth2UserInfoDto {
        val oAuth2Adapter = getOAuth2Adapter(registrationId)
        return oAuth2Adapter.getOAuth2UserInfo(accessToken)
    }

    fun withdrawUser(registrationId: String, userId: Long) {
        val user = userCommandHandler.withdrawUser(userId)

        when (OAuthProvider.valueOf(registrationId.uppercase())) {
            KAKAO -> {
                kakaoOAuth2Adapter.withdrawUser(user.oAuthInfo.oid.toLong())
            }

            NAVER -> {
                naverOAuth2Adapter.withdrawUser(userId)
            }
        }
    }

    private fun getOAuth2Adapter(registrationId: String): OAuth2Port {
        return when (OAuthProvider.valueOf(registrationId.uppercase())) {
            KAKAO -> kakaoOAuth2Adapter
            NAVER -> naverOAuth2Adapter
        }
    }
}
