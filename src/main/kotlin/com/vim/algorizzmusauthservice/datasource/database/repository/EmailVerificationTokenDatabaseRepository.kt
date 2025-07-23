package com.vim.algorizzmusauthservice.datasource.database.repository

import com.vim.algorizzmusauthservice.datasource.EmailVerificationTokenRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationTokenEntity
import com.vim.algorizzmusauthservice.datasource.database.repository.jpa.EmailVerificationTokenJPARepository
import org.springframework.stereotype.Component

@Component
class EmailVerificationTokenDatabaseRepository(private val jpaRepository: EmailVerificationTokenJPARepository) :
    EmailVerificationTokenRepository {
    override fun saveCode(code: EmailVerificationTokenEntity): EmailVerificationTokenEntity {
        return jpaRepository.save(code)
    }
}
