package com.dol.domain.follow.service.impl

import com.dol.domain.follow.presentation.data.response.FollowRecommendResponse
import com.dol.domain.follow.service.FollowRecommendService
import com.dol.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class FollowRecommendServiceImpl(
    private val userRepository: UserRepository
) : FollowRecommendService {
    override fun execute(): List<FollowRecommendResponse> {
        val allUser = userRepository.findAll()
        return allUser.map {
            FollowRecommendResponse(
                id = it.id,
                name = it.name,
                profileUrl = it.profileUrl
            )
        }
    }
}