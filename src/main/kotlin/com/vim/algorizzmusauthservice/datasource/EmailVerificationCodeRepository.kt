package com.vim.algorizzmusauthservice.datasource

import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationCodeEntity
import com.vim.algorizzmusauthservice.service.enums.VerificationCodeType
import java.util.Optional

interface EmailVerificationCodeRepository {
    fun saveCode(code: EmailVerificationCodeEntity): EmailVerificationCodeEntity

    fun findByCode(code: String): Optional<EmailVerificationCodeEntity>

    fun deleteById(id: Long)

    fun findByUserIdAndCodeType(
        userId: Long,
        codeType: VerificationCodeType,
    ): Optional<EmailVerificationCodeEntity>
}
