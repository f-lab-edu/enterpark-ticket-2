package com.example.enterparkticket.apis.enduser.controller.reservation

import com.example.enterparkticket.apis.enduser.controller.reservation.dto.request.CreateReservationRequest
import com.example.enterparkticket.apis.enduser.security.dto.AuthUser
import com.example.enterparkticket.apis.enduser.security.dto.UserPrincipal
import com.example.enterparkticket.domain.reservation.command.ReservationCommandHandler
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/v1/reservations")
class ReservationController(
    private val reservationCommandHandler: ReservationCommandHandler,
) {

    @PostMapping
    fun createReservation(
        @AuthUser user: UserPrincipal,
        @Valid @RequestBody request: CreateReservationRequest,
    ): ResponseEntity<String> {
        reservationCommandHandler.createReservation(user.userId, request.toCreateReservationDto())
        return ResponseEntity.status(CREATED).body("예매가 완료되었습니다.")
    }
}
