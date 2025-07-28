package com.vim.algorizzmusauthservice.datasource.database.repository

import com.vim.algorizzmusauthservice.datasource.UserRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.datasource.database.repository.jpa.UserJPARepository
import com.vim.algorizzmusauthservice.datasource.mapper.safeDbCall
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class UserDatabaseRepository(
    private val userJPARepository: UserJPARepository,
) : UserRepository {
    override fun saveUser(user: UserEntity): UserEntity =
        safeDbCall {
            userJPARepository.save(user)
        }

    override fun existsByUsername(username: String): Boolean =
        safeDbCall {
            userJPARepository.existsByUsername(username)
        }

    override fun existsByEmail(email: String): Boolean =
        safeDbCall {
            userJPARepository.existsByEmail(email)
        }

    override fun getUserByUsername(username: String): Optional<UserEntity> =
        safeDbCall {
            userJPARepository.getUserByUsername(username)
        }

    override fun findUserByEmail(email: String): Optional<UserEntity> =
        safeDbCall {
            userJPARepository.findUserByEmail(email)
        }
}
