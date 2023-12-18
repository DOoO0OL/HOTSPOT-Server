package com.dol.domain.hotplace.presentation.data.response

import java.util.UUID

data class QueryHotplaceResponse(
    val idx: UUID,
    val name: String,
    val address: String,
    val instagramId: String?,
    val recommend: Long,
    val imgURL: String?
)