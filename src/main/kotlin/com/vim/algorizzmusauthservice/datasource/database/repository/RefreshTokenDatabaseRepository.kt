package com.vim.algorizzmusauthservice.datasource.database.repository

import com.vim.algorizzmusauthservice.datasource.RefreshTokenRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.RefreshTokenEntity
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.datasource.database.repository.jpa.RefreshTokenJPARepository
import com.vim.algorizzmusauthservice.datasource.mapper.safeDbCall
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class RefreshTokenDatabaseRepository(
    private val jpaRepository: RefreshTokenJPARepository,
) : RefreshTokenRepository {
    override fun save(token: RefreshTokenEntity): RefreshTokenEntity =
        safeDbCall {
            jpaRepository.save(token)
        }

    override fun findByUser(user: UserEntity): Optional<RefreshTokenEntity> =
        safeDbCall {
            jpaRepository.findByUser(user)
        }

    override fun deleteById(tokenId: Long) =
        safeDbCall {
            jpaRepository.deleteById(tokenId)
        }

    override fun findByToken(token: String): Optional<RefreshTokenEntity> =
        safeDbCall {
            jpaRepository.findByToken(token)
        }
}
