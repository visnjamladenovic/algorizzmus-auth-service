package com.vim.algorizzmusauthservice.datasource.database.repository.jpa

import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationTokenEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmailVerificationTokenJPARepository : JpaRepository<EmailVerificationTokenEntity, Long> {
    fun findByToken(token: String): EmailVerificationTokenEntity?
}
