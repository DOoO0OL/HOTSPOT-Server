package com.dol.domain.user.service

import com.dol.domain.user.presentation.data.response.MyPageResponse

interface MyPageService {
    fun execute(): MyPageResponse
}