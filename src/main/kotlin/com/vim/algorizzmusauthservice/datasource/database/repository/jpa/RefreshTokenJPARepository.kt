package com.vim.algorizzmusauthservice.datasource.database.repository.jpa

import com.vim.algorizzmusauthservice.datasource.database.entity.RefreshTokenEntity
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RefreshTokenJPARepository : JpaRepository<RefreshTokenEntity, Long> {
    fun findByUser(user: UserEntity): Optional<RefreshTokenEntity>

    fun findByToken(token: String): Optional<RefreshTokenEntity>
}
