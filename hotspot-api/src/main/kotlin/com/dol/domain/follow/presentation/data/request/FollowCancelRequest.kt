package com.dol.domain.follow.presentation.data.request

import java.util.UUID

data class FollowCancelRequest(
    val toUserIdx: UUID
)