package com.dol.domain.auth.service

import com.dol.domain.auth.presentation.data.request.SignUpRequest

interface SignUpService {
    fun execute(signUpRequest: SignUpRequest)
}