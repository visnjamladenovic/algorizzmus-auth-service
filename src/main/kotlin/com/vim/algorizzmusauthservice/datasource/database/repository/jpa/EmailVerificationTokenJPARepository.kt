package com.vim.algorizzmusauthservice.datasource.database.repository.jpa

import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationTokenEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EmailVerificationTokenJPARepository : JpaRepository<EmailVerificationTokenEntity, Long>
