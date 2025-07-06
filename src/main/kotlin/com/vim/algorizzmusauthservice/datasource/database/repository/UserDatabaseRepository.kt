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

    override fun saveUser(userEntity: UserEntity): UserEntity {
        return userJPARepository.save(userEntity)
    }

    override fun findUserByUsername(username: String): Optional<UserEntity> {
        return userJPARepository.findByUsername(username)
    }

    override fun findUserByUsernameAndPassword(
        username: String,
        password: String,
    ): Optional<UserEntity> {
        return userJPARepository.findByUsernameAndPassword(username, password)
    }

    override fun deleteUserById(id: Long) {
        userJPARepository.deleteById(id)
    }
}
