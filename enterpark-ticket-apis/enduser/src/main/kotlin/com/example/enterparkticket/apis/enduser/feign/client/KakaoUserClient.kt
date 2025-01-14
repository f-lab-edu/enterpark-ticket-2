package com.example.enterparkticket.apis.enduser.feign.client

import com.example.enterparkticket.apis.enduser.feign.dto.response.KakaoUnlinkUserResponse
import com.example.enterparkticket.apis.enduser.feign.dto.response.KakaoUserInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "kakaoUserClient", url = "https://kapi.kakao.com")
interface KakaoUserClient {

    @GetMapping("/v2/user/me")
    fun getUserInfo(@RequestHeader("Authorization") accessToken: String): KakaoUserInfoResponse

    @PostMapping("/v1/user/unlink")
    fun unlinkUser(
        @RequestHeader("Authorization") adminKey: String,
        @RequestParam("target_id") targetId: Long,
        @RequestParam("target_id_type") targetIdType: String = "user_id",
    ): KakaoUnlinkUserResponse
}
