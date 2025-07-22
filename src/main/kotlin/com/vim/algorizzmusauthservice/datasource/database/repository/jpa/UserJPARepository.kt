package com.vim.algorizzmusauthservice.datasource.database.repository.jpa

import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserJPARepository : JpaRepository<UserEntity, Long> {
    fun existsByUsername(username: String): Boolean

    fun getUserByUsername(username: String): Optional<UserEntity>

    fun findUserByEmail(email: String): UserEntity?
}
