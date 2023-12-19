package com.dol.domain.hotplace.presentation.data.response

import java.util.UUID

data class QueryRecommendedHotplaceResponse(
    val idx: UUID,
    val name: String,
    val imgURL: String?
)