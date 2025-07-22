package com.vim.algorizzmusauthservice.datasource.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "name")
    var name: String = ""

    @Column(name = "surname")
    var surname: String = ""

    @Column(name = "username", unique = true)
    var username: String = ""

    @Column(name = "password")
    var password: String = ""

    @Column(name = "email", unique = true)
    var email: String = ""

    @Column(name = "is_verified")
    var isVerified: Boolean = false
}
