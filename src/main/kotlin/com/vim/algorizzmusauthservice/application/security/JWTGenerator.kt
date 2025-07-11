package com.vim.algorizzmusauthservice.application.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
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

    fun generateToken(authentication: Authentication): String {
        val authorities = authentication.authorities.map { it.authority }
        return Jwts.builder()
            .setSubject(authentication.name)
            .claim(AUTHORITIES, authorities)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(key)
            .compact()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    companion object {
        private const val AUTHORITIES = "authorities"
        private const val EXPIRATION = 3600000
    }
}
