package com.vim.algorizzmusauthservice.datasource

import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationCodeEntity
import java.util.Optional

interface EmailVerificationCodeRepository {
    fun saveCode(code: EmailVerificationCodeEntity): EmailVerificationCodeEntity

    fun findByCode(code: String): Optional<EmailVerificationCodeEntity>
}
