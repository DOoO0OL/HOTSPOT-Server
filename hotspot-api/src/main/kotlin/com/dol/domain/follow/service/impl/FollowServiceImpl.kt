package com.dol.domain.follow.service.impl

import com.dol.common.util.UserUtil
import com.dol.domain.follow.entity.Follow
import com.dol.domain.follow.exception.AlreadyFollowException
import com.dol.domain.follow.presentation.data.request.FollowRequest
import com.dol.domain.follow.repository.FollowRepository
import com.dol.domain.follow.service.FollowService
import com.dol.domain.user.exception.UserNotFoundException
import com.dol.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(rollbackFor = [Exception::class])
class FollowServiceImpl(
    private val userUtil: UserUtil,
    private val userRepository: UserRepository,
    private val followRepository: FollowRepository
) : FollowService {
    override fun execute(followRequest: FollowRequest) {
        val currentUserIdx = userUtil.getCurrentUserIdx()
        val toUser = userRepository.findByIdOrNull(followRequest.toUserIdx)
            ?: throw UserNotFoundException("유저가 존재하지 않습니다.")
        val fromUser = (userRepository.findByIdOrNull(currentUserIdx)
            ?: throw UserNotFoundException("유저가 존재하지 않습니다."))
        if (followRepository.existsByToUserAndFromUser(toUser, fromUser)) {
            throw AlreadyFollowException("이미 팔로우한 유저 입니다.")
        }

        val follow = Follow(
            idx = UUID.randomUUID(),
            toUser = toUser,
            fromUser = fromUser
        )
        followRepository.save(follow)
    }
}