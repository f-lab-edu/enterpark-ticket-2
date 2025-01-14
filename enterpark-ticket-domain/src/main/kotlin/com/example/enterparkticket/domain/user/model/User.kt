package com.example.enterparkticket.domain.user.model

import com.example.enterparkticket.domain.common.entity.BaseTimeEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(
        name = "uk_user_provider_user_id",
        columnNames = ["provider", "user_id"]
    )]
)
class User(
    oAuthInfo: OAuthInfo,
    name: String,
    email: String,
    phoneNumber: String,
    birthDate: LocalDate,
    gender: GenderType,
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long? = null
        protected set

    @Embedded
    var oAuthInfo: OAuthInfo = oAuthInfo
        protected set

    @Column(nullable = false, length = 20)
    var name: String = name
        protected set

    @Column(nullable = false, length = 255)
    var email: String = email
        protected set

    @Column(nullable = false, length = 20)
    var phoneNumber: String = phoneNumber
        protected set

    @Column(nullable = false)
    var birthDate: LocalDate = birthDate
        protected set

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var gender: GenderType = gender
        protected set

    @Column(nullable = true, length = 255)
    var address: String? = null
        protected set

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var role: RoleType = RoleType.USER
        protected set

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var state: StateType = StateType.NORMAL
        protected set

    fun withdrawUser(): Long {
        state = StateType.SUSPENDED
        deleteSoftly()
        return oAuthInfo.oid
    }

    fun updateUser(name: String, email: String, phoneNumber: String) {
        this.name = name
        this.email = email
        this.phoneNumber = phoneNumber
    }

    fun updateAddress(address: String) {
        this.address = address
    }
}
