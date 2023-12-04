package com.dol.domain.auth.service

import com.dol.domain.auth.presentation.data.response.TokenResponse

interface TokenReissueService {
    fun execute(refreshToken: String): TokenResponse
}