package com.vim.algorizzmusauthservice.datasource

import com.vim.algorizzmusauthservice.datasource.database.entity.RefreshTokenEntity
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import java.util.Optional

interface RefreshTokenRepository {
    fun save(token: RefreshTokenEntity): RefreshTokenEntity

    fun findByUser(user: UserEntity): Optional<RefreshTokenEntity>

    fun deleteById(tokenId: Long)

    fun findByToken(token: String): Optional<RefreshTokenEntity>
}
