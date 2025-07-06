package com.vim.algorizzmusauthservice.datasource.database.repository

import com.vim.algorizzmusauthservice.datasource.UserRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.datasource.database.repository.jpa.UserJPARepository
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class UserDatabaseRepository(
    private val userJPARepository: UserJPARepository,
) : UserRepository {
    override fun getUserById(id: Long): Optional<UserEntity> {
        return userJPARepository.findById(id)
    }
}
