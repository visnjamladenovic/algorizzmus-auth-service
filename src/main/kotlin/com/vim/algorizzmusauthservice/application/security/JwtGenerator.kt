package com.vim.algorizzmusauthservice.application.security

import com.vim.algorizzmusauthservice.application.enums.TokenType
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtGenerator {
    @Value("\${jwt.secret}")
    private lateinit var jwtSecret: String
    private val key by lazy {
        Keys.hmacShaKeyFor(jwtSecret.toByteArray(Charsets.UTF_8))
    }

    fun generateToken(
        authentication: Authentication,
        tokenType: TokenType,
    ): String {
        val authorities = authentication.authorities.map { it.authority }
        return Jwts.builder()
            .setSubject(authentication.name)
            .claim(AUTHORITIES, authorities)
            .setIssuedAt(Date())
            .setExpiration(calculateExpiration(tokenType))
            .signWith(key)
            .compact()
    }

    fun extractUsername(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun calculateExpiration(tokenType: TokenType): Date {
        return when (tokenType) {
            TokenType.ACCESS -> Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION)
            TokenType.REFRESH -> Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION)
        }
    }

    companion object {
        private const val AUTHORITIES = "authorities"
        private const val ACCESS_TOKEN_EXPIRATION = 3600000
        private const val REFRESH_TOKEN_EXPIRATION = 3600000 * 24 * 7
    }
}
