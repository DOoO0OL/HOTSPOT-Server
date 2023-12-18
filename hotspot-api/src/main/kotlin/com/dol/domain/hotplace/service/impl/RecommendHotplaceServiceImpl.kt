package com.dol.domain.hotplace.service.impl

import com.dol.common.util.UserUtil
import com.dol.domain.hotplace.exception.AlreadyRecommendedHotplaceException
import com.dol.domain.hotplace.exception.HotplaceNotFoundException
import com.dol.domain.hotplace.repository.HotplaceRepository
import com.dol.domain.hotplace.service.RecommendHotplaceService
import com.dol.domain.recommend.entity.Recommend
import com.dol.domain.recommend.repository.RecommendRepository
import com.dol.domain.user.exception.UserNotFoundException
import com.dol.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
@Transactional(rollbackFor = [Exception::class])
class RecommendHotplaceServiceImpl(
    private val userRepository: UserRepository,
    private val hotplaceRepository: HotplaceRepository,
    private val recommendRepository: RecommendRepository,
    private val userUtil: UserUtil
) : RecommendHotplaceService {
    override fun execute(idx: UUID) {
        val user = userRepository.findByIdOrNull(userUtil.getCurrentUserIdx())
            ?: throw UserNotFoundException("해당 유저를 찾을 수 없습니다. info [ userIdx = ${userUtil.getCurrentUserIdx()} ]")

        val hotplace = hotplaceRepository.findByIdOrNull(idx)
            ?: throw HotplaceNotFoundException("해당 핫플레이스를 찾을 수 없습니다. info [ userIdx = $idx")

        if(recommendRepository.existsByUserAndHotplace(user, hotplace))
            throw AlreadyRecommendedHotplaceException("이미 추천한 핫플레이스입니다. info [ hotplaceIdx = $idx ]")

        val recommend = Recommend(
            idx = UUID.randomUUID(),
            user = user,
            hotplace = hotplace
        )

        recommendRepository.save(recommend)
    }
}