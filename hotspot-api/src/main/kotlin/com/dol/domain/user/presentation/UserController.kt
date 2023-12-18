package com.dol.domain.user.presentation

import com.dol.domain.image.presentation.data.response.ImageUrlResponse
import com.dol.domain.user.presentation.data.response.MyPageResponse
import com.dol.domain.user.service.MyPageService
import com.dol.domain.user.service.UploadProfileImageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/user")
class UserController(
    private val myPageService: MyPageService,
    private val uploadProfileImageService: UploadProfileImageService
) {
    @GetMapping("/my-page")
    fun myPage(): ResponseEntity<MyPageResponse> =
        myPageService.execute()
            .let { ResponseEntity.ok(it) }

    @PostMapping("profileImage")
    fun uploadProfileImage(@RequestPart("image") file: MultipartFile) : ResponseEntity<ImageUrlResponse> =
        uploadProfileImageService.execute(file)
            .let { ResponseEntity.ok(it) }

}