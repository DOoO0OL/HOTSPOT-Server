package com.dol.domain.auth.repository

import com.dol.domain.auth.entity.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, String>