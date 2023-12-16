package com.dol.domain.image.presentation

import com.dol.domain.image.service.ImageUploadService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/image")
class ImageController(
    private val imageUploadService: ImageUploadService
) {
    @PostMapping
    fun uploadImage(@RequestParam("image") file: MultipartFile) : ResponseEntity<String> =
        imageUploadService.execute(file)
            .let { ResponseEntity.ok(it) }

}