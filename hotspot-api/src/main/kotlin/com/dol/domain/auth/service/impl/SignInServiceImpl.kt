package com.dol.domain.auth.service.impl

import com.dol.common.util.SecurityUtil
import com.dol.domain.auth.exception.PasswordNotMatchException
import com.dol.domain.auth.presentation.data.dto.TokenDto
import com.dol.domain.auth.presentation.data.request.SignInRequest
import com.dol.domain.auth.service.SignInService
import com.dol.domain.user.exception.UserNotFoundException
import com.dol.domain.user.repository.UserRepository
import com.dol.global.security.jwt.TokenGenerator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class SignInServiceImpl(
    private val userRepository: UserRepository,
    private val securityUtil: SecurityUtil,
    private val tokenGenerator: TokenGenerator
) : SignInService {
    override fun execute(signInRequest: SignInRequest): TokenDto {
        val user = userRepository.findById(signInRequest.id) ?: throw UserNotFoundException()
        if (!securityUtil.isPasswordMatch(signInRequest.password, user.password)) {
            throw PasswordNotMatchException()
        }
        return tokenGenerator.generateToken(user.idx, user.authority)
    }
}