package com.example.enterparkticket.domain.place.model

import com.example.enterparkticket.domain.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class Place(
    name: String,
    address: String,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    var id: Long? = null
        protected set

    @Column(nullable = false, length = 255)
    var name: String = name
        protected set

    @Column(nullable = false, length = 255)
    var address: String = address
        protected set
}
