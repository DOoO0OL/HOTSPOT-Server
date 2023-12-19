package com.dol.domain.hotplace.service.impl

import com.dol.domain.hotplace.presentation.data.request.QueryHotplaceListRequest
import com.dol.domain.hotplace.presentation.data.response.QueryHotplaceListResponse
import com.dol.domain.hotplace.repository.HotplaceRepository
import com.dol.domain.hotplace.service.QueryHotplaceListService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class QueryHotplaceListServiceImpl(
    private val hotplaceRepository: HotplaceRepository
) : QueryHotplaceListService{
    override fun execute(queryHotplaceListRequest: QueryHotplaceListRequest): List<QueryHotplaceListResponse> {
        val hotplaces = queryHotplaceListRequest.run {
            hotplaceRepository.findAllByLatitudeBetweenAndLongitudeBetween(
                startLatitude = latitude - 0.0091,
                endLatitude = latitude + 0.0091,
                startLongitude = longitude - 0.0113,
                endLongitude = longitude + 0.0113
            )
        }

        val response = hotplaces.map {
            QueryHotplaceListResponse(
                idx = it.idx,
                latitude = it.latitude,
                longitude = it.longitude
            )
        }

        return response
    }
}