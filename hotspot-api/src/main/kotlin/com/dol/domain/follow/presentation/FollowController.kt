package com.dol.domain.follow.presentation

import com.dol.domain.follow.presentation.data.request.FollowRequest
import com.dol.domain.follow.service.FollowCancelService
import com.dol.domain.follow.service.FollowService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

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

    @DeleteMapping("/{userIdx}")
    fun followCancel(@PathVariable("userIdx") toUserIdx: UUID) : ResponseEntity<Void> =
        followCancelService.execute(toUserIdx)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

}