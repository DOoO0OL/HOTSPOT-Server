package com.dol.domain.auth.service.impl

import com.dol.domain.auth.entity.RefreshToken
import com.dol.domain.auth.exception.ExpiredRefreshTokenException
import com.dol.domain.auth.presentation.data.response.TokenResponse
import com.dol.domain.auth.repository.RefreshTokenRepository
import com.dol.domain.auth.service.TokenReissueService
import com.dol.domain.user.entity.User
import com.dol.domain.user.exception.UserNotFoundException
import com.dol.domain.user.repository.UserRepository
import com.dol.global.security.jwt.TokenGenerator
import com.dol.global.security.jwt.TokenParser
import com.dol.global.security.jwt.common.exception.InvalidTokenTypeException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(rollbackFor = [Exception::class])
class TokenReissueServiceImpl(
    private val tokenParser: TokenParser,
    private val tokenGenerator: TokenGenerator,
    private val userRepository: UserRepository,
    private val refreshTokenRepository: RefreshTokenRepository
) : TokenReissueService {
    override fun execute(refreshToken: String): TokenResponse {
        val parsedRefreshToken = tokenParser.parseRefreshTokenToken(refreshToken)
            ?: throw InvalidTokenTypeException("유효하지 않은 토큰입니다. info : [ refreshToken = $refreshToken ]")
        val refreshTokenDomain = refreshTokenRepository.findByIdOrNull(parsedRefreshToken)
            ?: throw ExpiredRefreshTokenException("만료된 토큰입니다. info : [ refreshToken = $parsedRefreshToken ]")
        val user = userRepository.findByIdOrNull(refreshTokenDomain.userIdx)
            ?: throw UserNotFoundException("사용자를 찾을 수 없습니다.")
        val token = tokenGenerator.generateToken(user.idx, user.authority)
        refreshTokenRepository.delete(refreshTokenDomain)
        return TokenResponse(
            accessToken = token.accessToken,
            refreshToken = token.refreshToken,
            accessTokenExp = LocalDateTime.now().plusSeconds(token.accessTokenExp),
            refreshTokenExp = LocalDateTime.now().plusSeconds(token.refreshTokenExp),
        )
    }
}