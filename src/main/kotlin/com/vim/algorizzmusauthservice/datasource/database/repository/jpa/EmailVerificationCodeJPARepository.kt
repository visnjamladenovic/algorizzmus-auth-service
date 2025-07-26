package com.vim.algorizzmusauthservice.datasource.database.repository.jpa

import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationCodeEntity
import com.vim.algorizzmusauthservice.service.enums.VerificationCodeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface EmailVerificationCodeJPARepository : JpaRepository<EmailVerificationCodeEntity, Long> {
    fun findByCode(code: String): Optional<EmailVerificationCodeEntity>
    fun findByUserIdAndCodeType(userId: Long, codeType: VerificationCodeType): Optional<EmailVerificationCodeEntity>
}
