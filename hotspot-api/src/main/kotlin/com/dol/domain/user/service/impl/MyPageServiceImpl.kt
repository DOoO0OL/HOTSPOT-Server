package com.dol.domain.user.service.impl

import com.dol.common.util.UserUtil
import com.dol.domain.user.exception.UserNotFoundException
import com.dol.domain.user.presentation.data.response.MyPageResponse
import com.dol.domain.user.repository.UserRepository
import com.dol.domain.user.service.MyPageService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class], readOnly = true)
class MyPageServiceImpl(
    private val userRepository: UserRepository,
    private val userUtil: UserUtil
) : MyPageService {
    override fun execute(): MyPageResponse {
        val userIdx = userUtil.getCurrentUserIdx()
        val user = userRepository.findByIdOrNull(userIdx)
            ?: throw UserNotFoundException("사용자가 존재하지 않습니다.")

        return MyPageResponse(
            id = user.id,
            name = user.name,
            nickName = user.nickName,
            profileUrl = user.profileUrl
        )
    }
}