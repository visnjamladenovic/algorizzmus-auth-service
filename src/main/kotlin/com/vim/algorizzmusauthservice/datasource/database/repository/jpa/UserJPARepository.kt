package com.vim.algorizzmusauthservice.datasource.database.repository.jpa

import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJPARepository: JpaRepository<UserEntity, Long>