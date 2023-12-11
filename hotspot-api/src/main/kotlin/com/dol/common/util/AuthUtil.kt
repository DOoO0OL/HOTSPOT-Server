package com.dol.common.util

import com.dol.domain.auth.entity.AuthCode
import com.dol.domain.auth.entity.Authentication
import com.dol.domain.auth.repository.AuthCodeRepository
import com.dol.domain.auth.repository.AuthenticationRepository
import com.dol.thirdparty.coolSms.properties.CoolSmsExpProperties
import org.springframework.stereotype.Component

@Component
class AuthUtil(
    private val coolSmsExpProperties: CoolSmsExpProperties,
    private val authCodeRepository: AuthCodeRepository,
    private val authenticationRepository: AuthenticationRepository
) {
    fun saveAuthCode(phoneNumber: String, authCode: Int) {
        val authCodeDomain = AuthCode(
            phoneNumber = phoneNumber,
            authCode = authCode,
            expiredAt = coolSmsExpProperties.coolSmsExp
        )
        authCodeRepository.save(authCodeDomain)
    }
    fun saveAuthentication(phoneNumber: String) : Authentication {
        val smsAuthentication = Authentication(phoneNumber)
        authenticationRepository.save(smsAuthentication)
        return smsAuthentication
    }
}