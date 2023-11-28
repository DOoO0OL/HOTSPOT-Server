package com.dol.global.security.principal

import com.dol.domain.user.exception.UserNotFoundException
import com.dol.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(rollbackFor = [Exception::class], readOnly = true)
class AdminDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByIdOrNull(UUID.fromString(username))
            .let { it ?: throw UserNotFoundException() }
            .let { AdminDetails(it.idx) }

}