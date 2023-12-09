package com.dol.domain.follow.presentation

import com.dol.domain.follow.presentation.data.request.FollowCancelRequest
import com.dol.domain.follow.presentation.data.request.FollowRequest
import com.dol.domain.follow.service.FollowCancelService
import com.dol.domain.follow.service.FollowService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/follow")
class FollowController(
    private val followService: FollowService,
    private val followCancelService: FollowCancelService
) {

    @PostMapping
    fun follow(@RequestBody followRequest: FollowRequest) : ResponseEntity<Void> =
        followService.execute(followRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @DeleteMapping
    fun followCancel(@RequestBody followCancelRequest: FollowCancelRequest) : ResponseEntity<Void> =
        followCancelService.execute(followCancelRequest)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

}