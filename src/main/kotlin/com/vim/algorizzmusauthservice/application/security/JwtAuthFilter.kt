package com.vim.algorizzmusauthservice.application.security

import com.vim.algorizzmusauthservice.service.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtGenerator: JwtGenerator,
    private val userDetailsService: UserService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val publicPaths = listOf("/users/login", "/users/register")

        if (request.requestURI.contains(publicPaths[1])) {
            filterChain.doFilter(request, response)
            return
        }
        val authHeader = request.getHeader(AUTHORIZATION_HEADER)
        val token = authHeader?.takeIf { it.startsWith(BEARER) }?.substring(7)
        val username = token?.let { jwtGenerator.extractUsername(it) }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService.loadUserByUsername(username)

            if (jwtGenerator.validateToken(token)) {
                val authToken =
                    UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.authorities,
                    ).apply {
                        details = WebAuthenticationDetailsSource().buildDetails(request)
                    }
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        filterChain.doFilter(request, response)
    }

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val BEARER = "Bearer "
    }
}
