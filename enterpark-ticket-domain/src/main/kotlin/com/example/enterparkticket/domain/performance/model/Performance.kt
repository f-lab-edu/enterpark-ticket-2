package com.example.enterparkticket.domain.performance.model

import com.example.enterparkticket.domain.common.entity.BaseEntity
import com.example.enterparkticket.domain.common.entity.Money
import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Performance(
    title: String,
    imageUrl: String,
    description: String,
    startDate: LocalDate,
    endDate: LocalDate,
    totalTime: Int,
    ageLimit: AgeLimitType,
    fee: Money,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id")
    var id: Long? = null
        protected set

    @Column(nullable = false, length = 255)
    var title: String = title
        protected set

    @Column(nullable = false, length = 255)
    var imageUrl: String = imageUrl
        protected set

    @Column(nullable = false, length = 255)
    var description: String = description
        protected set

    @Column(nullable = false)
    var startDate: LocalDate = startDate
        protected set

    @Column(nullable = false)
    var endDate: LocalDate = endDate
        protected set

    @Column(nullable = false)
    var totalTime: Int = totalTime
        protected set

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var ageLimit: AgeLimitType = ageLimit
        protected set

    @Embedded
    var fee: Money = fee
        protected set
}
