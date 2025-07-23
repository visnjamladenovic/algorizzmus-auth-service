package com.vim.algorizzmusauthservice.datasource.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import kotlin.random.Random

@Entity
@Table(name = "email_verification_tokens")
class EmailVerificationTokenEntity(
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,
    @Column(nullable = false, unique = true)
    var code: String = Random.nextInt(1000, 9999).toString(),
    @Column(nullable = false)
    var expirationDate: LocalDateTime = LocalDateTime.now().plusHours(24),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
