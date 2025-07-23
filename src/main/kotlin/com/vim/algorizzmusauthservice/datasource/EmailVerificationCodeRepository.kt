package com.vim.algorizzmusauthservice.datasource

import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationCodeEntity

interface EmailVerificationCodeRepository {
    fun saveCode(code: EmailVerificationCodeEntity): EmailVerificationCodeEntity
}
