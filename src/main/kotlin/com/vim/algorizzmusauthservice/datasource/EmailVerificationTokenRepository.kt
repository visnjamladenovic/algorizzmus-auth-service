package com.vim.algorizzmusauthservice.datasource

import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationTokenEntity

interface EmailVerificationTokenRepository {
    fun saveCode(code: EmailVerificationTokenEntity): EmailVerificationTokenEntity
}
