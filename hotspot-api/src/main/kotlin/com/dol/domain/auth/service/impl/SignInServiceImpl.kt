package com.dol.domain.auth.service.impl

import com.dol.common.util.SecurityUtil
import com.dol.domain.auth.exception.PasswordNotMatchException
import com.dol.domain.auth.presentation.data.dto.TokenDto
import com.dol.domain.auth.presentation.data.request.SignInRequest
import com.dol.domain.auth.presentation.data.response.TokenResponse
import com.dol.domain.auth.service.SignInService
import com.dol.domain.user.exception.UserNotFoundException
import com.dol.domain.user.repository.UserRepository
import com.dol.global.security.jwt.TokenGenerator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(rollbackFor = [Exception::class])
class SignInServiceImpl(
    private val userRepository: UserRepository,
    private val securityUtil: SecurityUtil,
    private val tokenGenerator: TokenGenerator
) : SignInService {
    override fun execute(signInRequest: SignInRequest): TokenResponse {
        val user = userRepository.findById(signInRequest.id) 
            ?: throw UserNotFoundException()
        
        if (!securityUtil.isPasswordMatch(signInRequest.password, user.password))
            throw PasswordNotMatchException()
        
        val token =  tokenGenerator.generateToken(user.idx, user.authority)
        
        return TokenResponse(
            accessToken = token.accessToken,
            refreshToken = token.refreshToken,
            accessTokenExp = LocalDateTime.now().plusSeconds(token.accessTokenExp),
            refreshTokenExp = LocalDateTime.now().plusSeconds(token.refreshTokenExp),
        )
    }
}