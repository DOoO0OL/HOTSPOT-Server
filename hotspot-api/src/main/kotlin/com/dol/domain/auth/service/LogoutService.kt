package com.dol.domain.auth.service

interface LogoutService {
    fun execute(refreshToken: String)
}