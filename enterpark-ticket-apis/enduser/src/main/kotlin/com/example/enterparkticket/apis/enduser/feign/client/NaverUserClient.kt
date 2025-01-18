package com.example.enterparkticket.apis.enduser.feign.client

import com.example.enterparkticket.apis.enduser.feign.dto.response.NaverUserInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "naverUserClient", url = "https://openapi.naver.com")
interface NaverUserClient {

    @GetMapping("/v1/nid/me")
    fun getUserInfo(@RequestHeader("Authorization") accessToken: String): NaverUserInfoResponse
}
