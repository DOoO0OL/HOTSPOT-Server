package com.dol.domain.auth.repository

import com.dol.domain.auth.entity.AuthCode
import org.springframework.data.repository.CrudRepository

interface AuthCodeRepository : CrudRepository<AuthCode, String>