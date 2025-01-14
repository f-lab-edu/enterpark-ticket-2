package com.example.enterparkticket.apis.enduser.controller.auth

import com.example.enterparkticket.apis.enduser.security.dto.AuthUser
import com.example.enterparkticket.apis.enduser.security.dto.UserPrincipal
import com.example.enterparkticket.application.usecase.user.command.UserCommandHandler
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/v1/auth")
class AuthController(
    private val userCommandHandler: UserCommandHandler,
) {

    @PatchMapping("/oauth/kakao/withdrawal")
    fun kakaoOAuthWithdrawUser(@AuthUser user: UserPrincipal): ResponseEntity<String> {
        userCommandHandler.withdrawUser(user.userId)
        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.")
    }
}
