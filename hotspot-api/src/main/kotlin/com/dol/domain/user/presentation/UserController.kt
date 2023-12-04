package com.dol.domain.user.presentation

import com.dol.domain.user.presentation.data.response.MyPageResponse
import com.dol.domain.user.service.MyPageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val myPageService: MyPageService
) {
    @GetMapping("/my-page")
    fun myPage(): ResponseEntity<MyPageResponse> =
        myPageService.execute()
            .let { ResponseEntity.ok(it) }

}