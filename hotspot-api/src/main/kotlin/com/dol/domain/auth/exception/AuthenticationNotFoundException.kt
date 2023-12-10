package com.dol.domain.auth.exception

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException

class AuthenticationNotFoundException(message: String) : HotSpotException(ErrorStatus.AUTHENTICATION_NOT_FOUND, message)