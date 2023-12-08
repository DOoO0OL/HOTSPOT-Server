package com.dol.domain.follow.service

import com.dol.domain.follow.presentation.data.request.FollowRequest

interface FollowService {
    fun execute(followRequest: FollowRequest)
}