package com.dol.domain.auth.presentation

import com.dol.domain.auth.mapper.AuthMapper
import com.dol.domain.auth.presentation.data.request.SignInRequest
import com.dol.domain.auth.presentation.data.request.SignUpRequest
import com.dol.domain.auth.presentation.data.response.TokenResponse
import com.dol.domain.auth.service.SignInService
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
    private val authMapper: AuthMapper,
    private val signUpService: SignUpService,
    private val signInService: SignInService
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<Void> =
        signUpService.execute(signUpRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/signin")
    fun signIn(@RequestBody signInRequest: SignInRequest): ResponseEntity<TokenResponse> =
        signInService.execute(signInRequest)
            .let { ResponseEntity.ok(authMapper.toResponse(it)) }

}