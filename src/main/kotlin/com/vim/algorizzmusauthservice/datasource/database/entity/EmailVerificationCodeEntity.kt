package com.vim.algorizzmusauthservice.datasource.database.entity

import com.vim.algorizzmusauthservice.service.enums.VerificationCodeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import kotlin.random.Random

@Entity
@Table(name = "verification_codes")
class EmailVerificationCodeEntity(
    @Enumerated(EnumType.STRING)
    @Column(name = "verification_code_type", nullable = false)
    var codeType: VerificationCodeType,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "code", nullable = false, unique = true)
    var code: String = Random.nextInt(100000, 999999).toString()

    @Column(name = "expiration_date", nullable = false)
    var expirationDate: LocalDateTime = LocalDateTime.now().plusHours(24)
}
