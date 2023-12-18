package com.dol.domain.hotplace.service

import java.util.UUID

interface RecommendHotplaceService {
    fun execute(idx: UUID)
}