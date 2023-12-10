package com.dol.domain.follow.service

import java.util.UUID

interface FollowCancelService {
    fun execute(toUserIdx: UUID)
}