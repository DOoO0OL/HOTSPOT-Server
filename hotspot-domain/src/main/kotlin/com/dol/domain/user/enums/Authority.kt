package com.dol.domain.user.enums

import org.springframework.security.core.GrantedAuthority

enum class Authority : GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    override fun getAuthority(): String =
        name

}