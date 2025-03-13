package com.example.enterparkticket.domain.casting.model

import com.example.enterparkticket.domain.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class Casting(
    name: String,
    role: String,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "casting_id")
    var id: Long? = null
        protected set

    @Column(nullable = false, length = 15)
    var name: String = name
        protected set

    @Column(nullable = false, length = 20)
    var role: String = role
        protected set

    @Column(nullable = true, length = 255)
    var imageUrl: String? = null
        protected set
}
