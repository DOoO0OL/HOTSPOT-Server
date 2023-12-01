package com.dol.domain.auth.service

import com.dol.domain.auth.presentation.data.dto.TokenDto
import com.dol.domain.auth.presentation.data.request.SignInRequest

interface SignInService {
    fun execute(signInRequest: SignInRequest): TokenDto
}