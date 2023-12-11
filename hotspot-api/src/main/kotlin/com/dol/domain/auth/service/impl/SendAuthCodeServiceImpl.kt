package com.dol.domain.auth.service.impl

import com.dol.common.util.AuthUtil
import com.dol.domain.auth.exception.ManyAuthenticationRequestException
import com.dol.domain.auth.repository.AuthenticationRepository
import com.dol.domain.auth.service.SendAuthCodeService
import com.dol.thirdparty.coolSms.sender.CoolSmsSender
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(rollbackFor = [Exception::class])
class SendAuthCodeServiceImpl(
    private val authenticationRepository: AuthenticationRepository,
    private val coolSmsSender: CoolSmsSender,
    private val authUtil: AuthUtil
) : SendAuthCodeService {
    override fun execute(phoneNumber: String) {
        authenticationRepository.findByIdOrNull(phoneNumber)
            .let { it ?: authUtil.saveAuthentication(phoneNumber) }
            .let {
                if (it.attemptCount > 5)
                    throw ManyAuthenticationRequestException("요청 횟수를 초과하였습니다.")
                else
                    authenticationRepository.save(it.increaseAuthenticationCount())
            }
        val authCode = generateAuthCode()
        coolSmsSender.sendSms(phoneNumber, authCode)
        authUtil.saveAuthCode(phoneNumber, authCode)
    }

    private fun generateAuthCode() = Random().nextInt(8888) + 1111
}