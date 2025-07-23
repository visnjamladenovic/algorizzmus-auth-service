package com.vim.algorizzmusauthservice.datasource.emailservice.client

import com.vim.algorizzmusauthservice.datasource.emailservice.request.EmailCodeRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient("email-service", url = "http://localhost:8081")
interface EmailClient {
    @PostMapping("/send-verification-email")
    fun sendVerificationEmail(
        @RequestBody request: EmailCodeRequest,
    )
}
