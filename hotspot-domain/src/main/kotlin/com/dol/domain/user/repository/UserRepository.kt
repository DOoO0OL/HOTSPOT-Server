package com.dol.domain.user.repository

import com.dol.domain.user.entity.User
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UserRepository : CrudRepository<User, UUID> {
    fun existsById(id: String): Boolean
    fun existsByNickName(nickName: String): Boolean
    fun findById(id: String): User?
}