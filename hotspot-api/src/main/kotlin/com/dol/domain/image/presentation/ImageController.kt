package com.dol.domain.image.presentation

import com.dol.domain.image.presentation.data.response.ImageUrlResponse
import com.dol.domain.image.service.ImageUploadService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/image")
class ImageController(
    private val imageUploadService: ImageUploadService
) {
    @PostMapping
    fun uploadImage(@RequestPart("image") file: MultipartFile) : ResponseEntity<ImageUrlResponse> =
        imageUploadService.execute(file)
            .let { ResponseEntity.ok(it) }

}