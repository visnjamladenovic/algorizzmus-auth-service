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
import java.util.UUID

@Entity
@Table(name = "email_verification_tokens")
class EmailVerificationTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, unique = true)
    var token: String? = UUID.randomUUID().toString()

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity? = null

    @Column(nullable = false)
    var expirationDate: LocalDateTime = LocalDateTime.now().plusHours(24)

    @Column(nullable = false)
    var isVerified: Boolean = false
}
