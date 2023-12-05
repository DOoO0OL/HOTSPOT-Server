package com.dol.domain.auth.service.impl

import com.dol.domain.auth.exception.ExpiredRefreshTokenException
import com.dol.domain.auth.repository.RefreshTokenRepository
import com.dol.domain.auth.service.LogoutService
import com.dol.global.security.jwt.TokenParser
import com.dol.global.security.jwt.common.exception.InvalidTokenTypeException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class], readOnly = true)
class LogoutServiceImpl(
    private val tokenParser: TokenParser,
    private val refreshTokenRepository: RefreshTokenRepository
) : LogoutService {
    override fun execute(refreshToken: String) {
        val parseRefreshTokenToken = tokenParser.parseRefreshTokenToken(refreshToken)
            ?: throw InvalidTokenTypeException("유효하지 않은 토큰입니다. info : [ refreshToken = $refreshToken ]")
        val refreshTokenDomain = refreshTokenRepository.findByIdOrNull(parseRefreshTokenToken)
            ?: throw ExpiredRefreshTokenException("토큰이 만료되었습니다. info : [ refreshToken = $parseRefreshTokenToken ]")
        refreshTokenRepository.delete(refreshTokenDomain)
    }
}