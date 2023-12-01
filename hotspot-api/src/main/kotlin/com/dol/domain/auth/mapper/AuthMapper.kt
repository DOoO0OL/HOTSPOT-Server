package com.dol.domain.auth.mapper

import com.dol.domain.auth.presentation.data.dto.TokenDto
import com.dol.domain.auth.presentation.data.response.TokenResponse
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AuthMapper {

    fun toResponse(tokenDto: TokenDto): TokenResponse =
        TokenResponse(
            accessToken = tokenDto.accessToken,
            refreshToken =  tokenDto.refreshToken,
            accessTokenExp = LocalDateTime.now().plusSeconds(tokenDto.accessTokenExp),
            refreshTokenExp = LocalDateTime.now().plusSeconds(tokenDto.refreshTokenExp),
        )

}