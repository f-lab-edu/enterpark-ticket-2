package com.example.enterparkticket.domain.user.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
data class OAuthInfo(

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    val provider: OAuthProvider,

    @Column(nullable = false)
    val oid: String,
)
