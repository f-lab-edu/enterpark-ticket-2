package com.example.enterparkticket.apis.enduser.controller.user.dto.request

import com.example.enterparkticket.application.usecase.user.dto.UpdateUserDto
import jakarta.validation.constraints.Size

data class UpdateUserRequest(

    @field:Size(max = 255, message = "배송지는 0~255자만 가능합니다.")
    val address: String,
) {

    fun toUpdateUserDto(): UpdateUserDto {
        return UpdateUserDto(address)
    }
}
