package com.dol.domain.auth.presentation

import com.dol.domain.auth.presentation.data.request.SignInRequest
import com.dol.domain.auth.presentation.data.request.SignUpRequest
import com.dol.domain.auth.presentation.data.response.TokenResponse
import com.dol.domain.auth.service.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val signUpService: SignUpService,
    private val signInService: SignInService,
    private val tokenReissueService: TokenReissueService,
    private val logoutService: LogoutService,
    private val sendAuthCodeService: SendAuthCodeService,
    private val verifyAuthCodeService: VerifyAuthCodeService
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<Void> =
        signUpService.execute(signUpRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/signin")
    fun signIn(@RequestBody signInRequest: SignInRequest): ResponseEntity<TokenResponse> =
        signInService.execute(signInRequest)
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/reissue")
    fun reissueToken(@RequestHeader("RefreshToken") refreshToken: String): ResponseEntity<TokenResponse> =
        tokenReissueService.execute(refreshToken)
            .let { ResponseEntity.ok(it)}

    @DeleteMapping("/logout")
    fun logout(@RequestHeader("RefreshToken") refreshToken: String): ResponseEntity<Void> =
        logoutService.execute(refreshToken)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @PostMapping("/send/auth-code")
    fun sendAuthCode(@RequestParam("phoneNumber") phoneNumber: String): ResponseEntity<Void> =
        sendAuthCodeService.execute(phoneNumber)
            .let { ResponseEntity.status(HttpStatus.OK).build() }

    @PatchMapping("/verify/auth-code")
    fun verifyAuthCode(@RequestParam("phoneNumber") phoneNumber: String, @RequestParam("authCode") authCode: Int): ResponseEntity<Void> =
        verifyAuthCodeService.execute(phoneNumber, authCode)
            .let { ResponseEntity.status(HttpStatus.OK).build() }

}