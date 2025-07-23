package com.vim.algorizzmusauthservice.datasource.database.repository

import com.vim.algorizzmusauthservice.datasource.EmailVerificationCodeRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationCodeEntity
import com.vim.algorizzmusauthservice.datasource.database.repository.jpa.EmailVerificationCodeJPARepository
import org.springframework.stereotype.Component

@Component
class EmailVerificationCodeDatabaseRepository(private val jpaRepository: EmailVerificationCodeJPARepository) :
    EmailVerificationCodeRepository {
    override fun saveCode(code: EmailVerificationCodeEntity): EmailVerificationCodeEntity {
        return jpaRepository.save(code)
    }
}
