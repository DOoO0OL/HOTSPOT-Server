package com.dol.domain.auth.service.impl

import com.dol.domain.auth.exception.AuthCodeNotFoundException
import com.dol.domain.auth.exception.AuthCodeNotMatchException
import com.dol.domain.auth.exception.AuthenticationNotFoundException
import com.dol.domain.auth.exception.ManyAuthenticationRequestException
import com.dol.domain.auth.repository.AuthCodeRepository
import com.dol.domain.auth.repository.AuthenticationRepository
import com.dol.domain.auth.service.VerifyAuthCodeService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class VerifyAuthCodeServiceImpl(
    private val authCodeRepository: AuthCodeRepository,
    private val authenticationRepository: AuthenticationRepository
) : VerifyAuthCodeService {
    override fun execute(phoneNumber: String, authCode: Int) {
        val authenticationEntity = authenticationRepository.findByIdOrNull(phoneNumber)
            ?: throw AuthenticationNotFoundException("시간이 만료되었습니다.")
        val authCodeEntity = authCodeRepository.findByIdOrNull(phoneNumber)
            ?: throw AuthCodeNotFoundException("인증코드를 찾을 수 없습니다. info : [ phoneNumber = $phoneNumber ]")

        if (authenticationEntity.attemptCount > 5)
            throw ManyAuthenticationRequestException("인증 코드 확인 요청을 5번 초과 한 사용자 입니다. info : [ phoneNumber = ${authenticationEntity.phoneNumber} ]")

        if (authCode != authCodeEntity.authCode) {
            authenticationRepository.save(authenticationEntity.increaseAuthenticationCount())
            throw AuthCodeNotMatchException("인증번호가 일치하지 않습니다. info : [ authCode = $authCode ]")
        }
        authenticationRepository.save(authenticationEntity.certified())
    }
}