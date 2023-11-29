package com.dol.domain.auth.presentation

import com.dol.domain.auth.presentation.data.request.SignUpRequest
import com.dol.domain.auth.service.SignUpService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val signUpService: SignUpService
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<Void> =
        signUpService.execute(signUpRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

}