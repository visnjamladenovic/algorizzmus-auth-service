package com.vim.algorizzmusauthservice.datasource.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "refresh_tokens")
class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    open var id: Long = 0

    @Column(name = "token", nullable = false, unique = true)
    open var token: String = ""

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    open var user: UserEntity? = null
}
