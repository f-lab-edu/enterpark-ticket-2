package com.example.enterparkticket.domain.payment.model

import com.example.enterparkticket.domain.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class Payment(
    methodType: MethodType,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    var id: Long? = null
        protected set

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var method: MethodType = methodType
        protected set
}
