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

    override fun saveUser(user: UserEntity): UserEntity {
        return userJPARepository.save(user)
    }

    override fun existsByUsername(username: String): Boolean {
        return userJPARepository.existsByUsername(username)
    }

    override fun getUserByUsername(username: String): Optional<UserEntity> {
        return userJPARepository.getUserByUsername(username)
    }
}
