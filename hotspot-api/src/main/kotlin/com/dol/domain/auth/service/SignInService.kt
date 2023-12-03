package com.dol.domain.auth.service

import com.dol.domain.auth.presentation.data.request.SignInRequest
import com.dol.domain.auth.presentation.data.response.TokenResponse

interface SignInService {
    fun execute(signInRequest: SignInRequest): TokenResponse
}