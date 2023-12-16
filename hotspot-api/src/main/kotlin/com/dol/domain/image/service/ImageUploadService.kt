package com.dol.domain.image.service

import org.springframework.web.multipart.MultipartFile

interface ImageUploadService {
    fun execute(file: MultipartFile): String
}