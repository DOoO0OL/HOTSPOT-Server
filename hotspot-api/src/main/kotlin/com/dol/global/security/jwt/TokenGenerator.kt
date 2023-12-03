package com.dol.global.security.jwt

import com.dol.domain.auth.entity.RefreshToken
import com.dol.domain.auth.presentation.data.dto.TokenDto
import com.dol.domain.auth.repository.RefreshTokenRepository
import com.dol.domain.user.enums.Authority
import com.dol.global.security.jwt.common.properties.JwtExpTimeProperties
import com.dol.global.security.jwt.common.properties.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenGenerator(
    private val jwtProperties: JwtProperties,
    private val jwtExpTimeProperties: JwtExpTimeProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun generateToken(userIdx: UUID, authority: Authority): TokenDto =
        TokenDto(
            accessToken = generateAccessToken(userIdx, authority),
            refreshToken = generateRefreshToken(userIdx),
            accessTokenExp = jwtExpTimeProperties.accessExp.toLong(),
            refreshTokenExp = jwtExpTimeProperties.refreshExp.toLong(),
        )

    private fun generateAccessToken(userIdx: UUID, authority: Authority): String =
        Jwts.builder()
            .signWith(jwtProperties.accessSecret, SignatureAlgorithm.HS256)
            .setSubject(userIdx.toString())
            .claim(JwtProperties.TOKEN_TYPE, JwtProperties.ACCESS)
            .claim(JwtProperties.AUTHORITY, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.accessExp * 1000))
            .compact()

    private fun generateRefreshToken(userIdx: UUID): String {
        val refreshToken = Jwts.builder()
            .signWith(jwtProperties.refreshSecret, SignatureAlgorithm.HS256)
            .setSubject(userIdx.toString())
            .claim(JwtProperties.TOKEN_TYPE, JwtProperties.REFRESH)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.refreshExp * 1000))
            .compact()

        refreshTokenRepository.save(RefreshToken(refreshToken, userIdx, jwtExpTimeProperties.refreshExp.toLong()))
        return refreshToken
    }

}