package com.dol.domain.hotplace.presentation.data.response

import java.util.UUID

data class QueryHotplaceListResponse(
    val idx: UUID,
    val latitude: Double,
    val longitude: Double
)