package com.dol.domain.follow.repository

import com.dol.domain.follow.entity.Follow
import com.dol.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FollowRepository : JpaRepository<Follow, UUID> {
    fun existsByToUserAndFromUser(toUser: User, fromUser: User): Boolean
}