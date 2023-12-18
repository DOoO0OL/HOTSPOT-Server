package com.dol.domain.user.service

import com.dol.domain.image.presentation.data.response.ImageUrlResponse
import org.springframework.web.multipart.MultipartFile

interface UploadProfileImageService {
    fun execute(file: MultipartFile) : ImageUrlResponse
}