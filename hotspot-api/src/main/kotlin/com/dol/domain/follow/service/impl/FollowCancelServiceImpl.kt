package com.dol.domain.follow.service.impl

import com.dol.common.util.UserUtil
import com.dol.domain.follow.exception.AlreadyFollowException
import com.dol.domain.follow.repository.FollowRepository
import com.dol.domain.follow.repository.custom.CustomFollowRepository
import com.dol.domain.follow.service.FollowCancelService
import com.dol.domain.user.exception.UserNotFoundException
import com.dol.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(rollbackFor = [Exception::class])
class FollowCancelServiceImpl(
    private val userUtil: UserUtil,
    private val userRepository: UserRepository,
    private val followRepository: FollowRepository,
    private val customFollowRepository: CustomFollowRepository
) : FollowCancelService {
    override fun execute(toUserIdx: UUID) {
        val currentUserIdx = userUtil.getCurrentUserIdx()
        val fromUser = userRepository.findByIdOrNull(currentUserIdx)
            ?: throw UserNotFoundException("유저가 존재하지 않습니다.")
        val toUser = (userRepository.findByIdOrNull(toUserIdx)
            ?: throw UserNotFoundException("유저가 존재하지 않습니다."))
        if (!followRepository.existsByToUserAndFromUser(toUser, fromUser)) {
            throw AlreadyFollowException("팔로우 되어있지 않은 유저입니다.")
        }
        customFollowRepository.delete(toUser, fromUser)
    }
}