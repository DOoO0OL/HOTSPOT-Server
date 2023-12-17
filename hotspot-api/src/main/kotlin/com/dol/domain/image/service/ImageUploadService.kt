package com.dol.domain.image.service

import com.dol.domain.image.presentation.data.response.ImageUrlResponse
import org.springframework.web.multipart.MultipartFile

interface ImageUploadService {
    fun execute(file: MultipartFile): ImageUrlResponse
}