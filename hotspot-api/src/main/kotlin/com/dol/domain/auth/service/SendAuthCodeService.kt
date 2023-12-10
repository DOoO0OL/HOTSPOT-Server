package com.dol.domain.auth.service

interface SendAuthCodeService {
    fun execute(phoneNumber: String)
}