package com.vim.algorizzmusauthservice.application.controller

import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @GetMapping("/{id}")
    fun getUserById(
        @PathVariable id: Long,
    ): ResponseEntity<UserEntity> {
        return ResponseEntity.ok(
            userService.getUserById(id).get(),
        )
    }

    @PostMapping("/register")
    fun registerUser(
        @RequestBody user: UserEntity,
    ): ResponseEntity<UserEntity> {
        return ResponseEntity.ok(userService.registerUser(user))
    }
}
