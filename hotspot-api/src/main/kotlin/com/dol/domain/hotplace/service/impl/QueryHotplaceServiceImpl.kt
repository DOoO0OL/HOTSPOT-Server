package com.dol.domain.hotplace.service.impl

import com.dol.domain.hotplace.exception.HotplaceNotFoundException
import com.dol.domain.hotplace.presentation.data.response.QueryHotplaceResponse
import com.dol.domain.hotplace.repository.HotplaceRepository
import com.dol.domain.hotplace.service.QueryHotplaceService
import com.dol.domain.recommend.repository.RecommendRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class QueryHotplaceServiceImpl(
    private val hotplaceRepository: HotplaceRepository,
    private val recommendRepository: RecommendRepository
) : QueryHotplaceService{
    override fun execute(idx: UUID): QueryHotplaceResponse {
        val hotplace = hotplaceRepository.findByIdOrNull(idx)
            ?: throw HotplaceNotFoundException("핫플레이스를 찾을 수 없습니다. info [ userIdx = $idx ]")

        val recommend = recommendRepository.getHotplaceRecommendCount(hotplace) ?: 0L

        val response = QueryHotplaceResponse(
            idx = hotplace.idx,
            name = hotplace.name,
            address = hotplace.address,
            instagramId = hotplace.instagramId,
            recommend = recommend,
            imgURL = hotplace.imgURL
        )

        return response
    }
}