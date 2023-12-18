package com.dol.domain.hotplace.service

import com.dol.domain.hotplace.presentation.data.request.CreateHotplaceRequest

interface CreateHotplaceService {
    fun execute(createHotplaceRequest: CreateHotplaceRequest)
}