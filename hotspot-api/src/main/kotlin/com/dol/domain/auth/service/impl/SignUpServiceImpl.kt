package com.dol.domain.auth.service.impl

import com.dol.common.util.SecurityUtil
import com.dol.domain.auth.exception.DuplicateIdException
import com.dol.domain.auth.exception.DuplicateNickNameException
import com.dol.domain.auth.presentation.data.request.SignUpRequest
import com.dol.domain.auth.service.SignUpService
import com.dol.domain.user.entity.User
import com.dol.domain.user.enums.Authority
import com.dol.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(rollbackFor = [Exception::class])
class SignUpServiceImpl(
    private val userRepository: UserRepository,
    private val securityUtil: SecurityUtil
) : SignUpService {
    override fun execute(signUpRequest: SignUpRequest) {
        if (userRepository.existsById(signUpRequest.id)) throw DuplicateIdException("이미 존재하는 id 입니다.")
        if (userRepository.existsByNickName(signUpRequest.nickName)) throw DuplicateNickNameException("이미 존재하는 닉네임입니다.")

        val user = User(
            idx = UUID.randomUUID(),
            id = signUpRequest.id,
            name = signUpRequest.name,
            nickName = signUpRequest.nickName,
            phoneNumber = signUpRequest.phoneNumber,
            password = securityUtil.passwordEncode(signUpRequest.password),
            city = signUpRequest.city,
            gu = signUpRequest.gu,
            profileUrl = signUpRequest.profileUrl,
            authority = Authority.ROLE_USER
        )
        userRepository.save(user)
    }
}