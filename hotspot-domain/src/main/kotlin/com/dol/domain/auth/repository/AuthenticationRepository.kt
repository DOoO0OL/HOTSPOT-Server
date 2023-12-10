package com.dol.domain.auth.repository

import com.dol.domain.auth.entity.Authentication
import org.springframework.data.repository.CrudRepository

interface AuthenticationRepository : CrudRepository<Authentication, String> {
    fun existsByPhoneNumber(phoneNumber: String): Boolean
}