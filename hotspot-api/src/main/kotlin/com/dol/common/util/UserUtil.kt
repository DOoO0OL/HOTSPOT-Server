package com.dol.common.util

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserUtil {

    fun getCurrentUserIdx(): UUID =
        UUID.fromString(SecurityContextHolder.getContext().authentication.name)

}