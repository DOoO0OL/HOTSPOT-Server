package com.dol.domain.follow.service

import com.dol.domain.follow.presentation.data.request.FollowCancelRequest

interface FollowCancelService {
    fun execute(followCancelRequest: FollowCancelRequest)
}