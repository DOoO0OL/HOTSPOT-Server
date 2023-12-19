package com.dol.domain.hotplace.service

import com.dol.domain.hotplace.presentation.data.request.QueryHotplaceListRequest
import com.dol.domain.hotplace.presentation.data.response.QueryHotplaceListResponse

interface QueryHotplaceListService {
    fun execute(queryHotplaceListRequest: QueryHotplaceListRequest): List<QueryHotplaceListResponse>
}