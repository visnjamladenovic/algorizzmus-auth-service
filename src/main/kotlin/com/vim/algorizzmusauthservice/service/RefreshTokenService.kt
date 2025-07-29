package com.vim.algorizzmusauthservice.service

import com.vim.algorizzmusauthservice.application.security.JwtGenerator
import com.vim.algorizzmusauthservice.datasource.RefreshTokenRepository
import com.vim.algorizzmusauthservice.datasource.UserRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.RefreshTokenEntity
import com.vim.algorizzmusauthservice.service.exception.TokenNotFoundException
import com.vim.algorizzmusauthservice.service.exception.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class RefreshTokenService(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository,
    private val jwtGenerator: JwtGenerator,
) {
    fun save(
        token: String,
        username: String,
    ) {
        val user =
            userRepository.getUserByUsername(username).orElseThrow {
                throw UserNotFoundException("User $username not found")
            }
        val existingRefreshToken = refreshTokenRepository.findByUser(user)
        val newRefreshToken =
            RefreshTokenEntity(
                token = token,
                user = user,
            )
        if (existingRefreshToken.isPresent) {
            rotateRefreshToken(existingRefreshToken.get(), newRefreshToken)
        } else {
            refreshTokenRepository.save(newRefreshToken)
        }
    }

    fun validateRefreshToken(refreshToken: String) {
        refreshTokenRepository.findByToken(refreshToken).orElseThrow {
            throw TokenNotFoundException("Token $refreshToken not found")
        }
        jwtGenerator.validateToken(refreshToken)
    }

    private fun rotateRefreshToken(
        expiredToken: RefreshTokenEntity,
        validToken: RefreshTokenEntity,
    ) {
        refreshTokenRepository.deleteById(expiredToken.id)
        refreshTokenRepository.save(validToken)
    }
}
