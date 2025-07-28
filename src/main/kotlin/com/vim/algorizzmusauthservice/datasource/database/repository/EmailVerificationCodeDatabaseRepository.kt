package com.vim.algorizzmusauthservice.datasource.database.repository

import com.vim.algorizzmusauthservice.datasource.EmailVerificationCodeRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationCodeEntity
import com.vim.algorizzmusauthservice.datasource.database.repository.jpa.EmailVerificationCodeJPARepository
import com.vim.algorizzmusauthservice.datasource.mapper.safeDbCall
import com.vim.algorizzmusauthservice.service.enums.VerificationCodeType
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class EmailVerificationCodeDatabaseRepository(
    private val jpaRepository: EmailVerificationCodeJPARepository,
) : EmailVerificationCodeRepository {
    override fun saveCode(code: EmailVerificationCodeEntity): EmailVerificationCodeEntity =
        safeDbCall {
            jpaRepository.save(code)
        }

    override fun findByCode(code: String): Optional<EmailVerificationCodeEntity> =
        safeDbCall {
            jpaRepository.findByCode(code)
        }

    override fun deleteById(id: Long) =
        safeDbCall {
            jpaRepository.deleteById(id)
        }

    override fun findByUserIdAndCodeType(
        userId: Long,
        codeType: VerificationCodeType,
    ): Optional<EmailVerificationCodeEntity> =
        safeDbCall {
            jpaRepository.findByUserIdAndCodeType(userId, codeType)
        }
}
