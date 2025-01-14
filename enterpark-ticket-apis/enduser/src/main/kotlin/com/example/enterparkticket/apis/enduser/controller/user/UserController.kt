package com.example.enterparkticket.apis.enduser.controller.user

import com.example.enterparkticket.apis.enduser.controller.user.dto.request.UpdateUserRequest
import com.example.enterparkticket.apis.enduser.security.dto.AuthUser
import com.example.enterparkticket.apis.enduser.security.dto.UserPrincipal
import com.example.enterparkticket.application.usecase.user.command.UserCommandHandler
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/v1/users")
class UserController(private val userCommandHandler: UserCommandHandler) {

    @PatchMapping("/update")
    fun updateUserAddress(
        @AuthUser user: UserPrincipal,
        @Valid @RequestBody request: UpdateUserRequest,
    ): ResponseEntity<String> {
        userCommandHandler.updateUserAddress(user.userId, request.toUpdateUserDto())
        return ResponseEntity.ok("회원 정보 수정이 완료되었습니다.")
    }
}
