package com.dol.domain.auth.service.impl

import com.dol.domain.auth.entity.AuthCode
import com.dol.domain.auth.entity.Authentication
import com.dol.domain.auth.exception.AuthenticationNotFoundException
import com.dol.domain.auth.exception.ManyAuthenticationRequestException
import com.dol.domain.auth.repository.AuthCodeRepository
import com.dol.domain.auth.repository.AuthenticationRepository
import com.dol.domain.auth.service.SendAuthCodeService
import com.dol.thirdparty.coolSms.sender.CoolSmsSender
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class SendAuthCodeServiceImpl(
    private val authCodeRepository: AuthCodeRepository,
    private val authenticationRepository: AuthenticationRepository,
    private val coolSmsSender: CoolSmsSender
) : SendAuthCodeService {
    override fun execute(phoneNumber: String) {
        val isExistsAuthentication = authenticationRepository.existsByPhoneNumber(phoneNumber)

        if (isExistsAuthentication) {
            authenticationRepository.findByIdOrNull(phoneNumber)
                .let { it ?: throw AuthenticationNotFoundException("인증되지 않은 전화번호입니다.") }
                .let {
                    if (it.attemptCount > 5)
                        throw ManyAuthenticationRequestException("요청 횟수를 초과하였습니다.")
                    else
                        authenticationRepository.save(it.increaseAuthenticationCount())
                }
        }

        val authCode = generateAuthCode(9999)
        coolSmsSender.sendSms(phoneNumber, authCode)

        val authCodeDomain = AuthCode(
            phoneNumber = phoneNumber,
            authCode = authCode,
            expiredAt = 300
        )
        authCodeRepository.save(authCodeDomain)

        if (!isExistsAuthentication) {
            val smsAuthentication = Authentication(phoneNumber)
            authenticationRepository.save(smsAuthentication)
        }

    }

    private fun generateAuthCode(number: Int = 9999): Int = (0..number).random()
}