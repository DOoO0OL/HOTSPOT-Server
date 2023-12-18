package com.dol.domain.hotplace.service

import com.dol.domain.hotplace.presentation.data.response.QueryHotplaceResponse
import java.util.UUID

interface QueryHotplaceService {
    fun execute(idx: UUID): QueryHotplaceResponse
}