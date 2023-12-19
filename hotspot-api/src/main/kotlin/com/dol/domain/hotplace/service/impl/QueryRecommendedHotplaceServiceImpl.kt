package com.dol.domain.hotplace.service.impl

import com.dol.common.util.UserUtil
import com.dol.domain.hotplace.presentation.data.response.QueryRecommendedHotplaceResponse
import com.dol.domain.hotplace.repository.HotplaceRepository
import com.dol.domain.hotplace.service.QueryRecommendedHotplaceService
import com.dol.domain.user.exception.UserNotFoundException
import com.dol.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryRecommendedHotplaceServiceImpl(
    private val hotplaceRepository: HotplaceRepository,
    private val userRepository: UserRepository,
    private val userUtil: UserUtil
) : QueryRecommendedHotplaceService {
    override fun execute(): List<QueryRecommendedHotplaceResponse> {
        val user = userRepository.findByIdOrNull(userUtil.getCurrentUserIdx())
            ?: throw UserNotFoundException("해당 유저를 찾을 수 없습니다. info [ userIdx = ${userUtil.getCurrentUserIdx()} ]")

        val hotplaces = hotplaceRepository.queryAllRecommendedHotplace(user)

        val response = hotplaces.map {
            QueryRecommendedHotplaceResponse(
                idx = it.idx,
                name = it.name,
                imgURL = it.imgURL
            )
        }

        return response
    }
}