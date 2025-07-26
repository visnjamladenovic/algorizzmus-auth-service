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
    override fun saveUser(user: UserEntity): UserEntity {
        return userJPARepository.save(user)
    }

    override fun existsByUsername(username: String): Boolean {
        return userJPARepository.existsByUsername(username)
    }

    override fun existsByEmail(email: String): Boolean {
        return userJPARepository.existsByEmail(email)
    }

    override fun getUserByUsername(username: String): Optional<UserEntity> {
        return userJPARepository.getUserByUsername(username)
    }

    override fun findUserByEmail(email: String): Optional<UserEntity> {
        return userJPARepository.findUserByEmail(email)
    }
}
