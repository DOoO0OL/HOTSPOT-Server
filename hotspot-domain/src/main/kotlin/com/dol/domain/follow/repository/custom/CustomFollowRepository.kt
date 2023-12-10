package com.dol.domain.follow.repository.custom

import com.dol.domain.user.entity.User

interface CustomFollowRepository {
    fun delete(toUser: User, fromUser: User)
}