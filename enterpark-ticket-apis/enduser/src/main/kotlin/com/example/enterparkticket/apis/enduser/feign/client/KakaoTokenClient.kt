package com.example.enterparkticket.apis.enduser.feign.client

import com.example.enterparkticket.apis.enduser.feign.dto.request.KakaoTokenRequest
import com.example.enterparkticket.apis.enduser.feign.dto.response.KakaoTokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(name = "kakaoTokenClient", url = "https://kauth.kakao.com")
interface KakaoTokenClient {

    @PostMapping("/oauth/token", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun getToken(request: KakaoTokenRequest): KakaoTokenResponse
}
