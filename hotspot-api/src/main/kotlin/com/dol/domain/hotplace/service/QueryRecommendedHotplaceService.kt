package com.dol.domain.hotplace.service

import com.dol.domain.hotplace.presentation.data.response.QueryRecommendedHotplaceResponse

interface QueryRecommendedHotplaceService {
    fun execute(): List<QueryRecommendedHotplaceResponse>
}