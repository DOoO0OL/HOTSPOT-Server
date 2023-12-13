package com.dol.domain.follow.service

import com.dol.domain.follow.presentation.data.response.FollowRecommendResponse

interface FollowRecommendService {
    fun execute(): List<FollowRecommendResponse>
}