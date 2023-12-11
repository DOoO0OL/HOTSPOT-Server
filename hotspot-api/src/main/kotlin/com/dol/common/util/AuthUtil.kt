package com.dol.common.util

import com.dol.domain.auth.entity.AuthCode
import com.dol.domain.auth.entity.Authentication
import com.dol.domain.auth.repository.AuthCodeRepository
import com.dol.domain.auth.repository.AuthenticationRepository
import org.springframework.stereotype.Component

@Component
class AuthUtil(
    private val authCodeRepository: AuthCodeRepository,
    private val authenticationRepository: AuthenticationRepository
) {
    companion object {
        const val SMS_EXP = 300
    }
    fun saveAuthCode(phoneNumber: String, authCode: Int) {
        val authCodeDomain = AuthCode(
            phoneNumber = phoneNumber,
            authCode = authCode,
            expiredAt = SMS_EXP
        )
        authCodeRepository.save(authCodeDomain)
    }
    fun saveAuthentication(phoneNumber: String) : Authentication {
        val smsAuthentication = Authentication(phoneNumber)
        authenticationRepository.save(smsAuthentication)
        return smsAuthentication
    }
}