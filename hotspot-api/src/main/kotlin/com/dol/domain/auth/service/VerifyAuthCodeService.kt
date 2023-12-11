package com.dol.domain.auth.service

interface VerifyAuthCodeService {
    fun execute(phoneNumber: String, authCode: Int)
}