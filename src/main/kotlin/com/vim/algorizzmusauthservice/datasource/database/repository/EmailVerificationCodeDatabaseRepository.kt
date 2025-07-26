package com.vim.algorizzmusauthservice.datasource.database.repository

import com.vim.algorizzmusauthservice.datasource.EmailVerificationCodeRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationCodeEntity
import com.vim.algorizzmusauthservice.datasource.database.repository.jpa.EmailVerificationCodeJPARepository
import com.vim.algorizzmusauthservice.service.enums.VerificationCodeType
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class EmailVerificationCodeDatabaseRepository(private val jpaRepository: EmailVerificationCodeJPARepository) :
    EmailVerificationCodeRepository {
    override fun saveCode(code: EmailVerificationCodeEntity): EmailVerificationCodeEntity {
        return jpaRepository.save(code)
    }

    override fun findByCode(code: String): Optional<EmailVerificationCodeEntity> {
        return jpaRepository.findByCode(code)
    }

    override fun deleteById(id: Long) {
        jpaRepository.deleteById(id)
    }

    override fun findByUserIdAndCodeType(
        userId: Long,
        codeType: VerificationCodeType,
    ): Optional<EmailVerificationCodeEntity> {
        return jpaRepository.findByUserIdAndCodeType(userId, codeType)
    }
}
