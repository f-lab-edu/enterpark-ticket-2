package com.example.enterparkticket.apis.enduser.feign.client

import com.example.enterparkticket.apis.enduser.feign.dto.response.NaverDeleteUserResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "naverTokenClient", url = "https://nid.naver.com")
interface NaverTokenClient {

    @PostMapping("/oauth2.0/token")
    fun deleteUser(
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("access_token") accessToken: String,
        @RequestParam("grant_type") grantType: String = "delete",
        @RequestParam("service_provider") serviceProvider: String = "NAVER",
    ): NaverDeleteUserResponse
}
