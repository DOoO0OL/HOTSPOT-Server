package com.dol.domain.auth.exception

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException

class AuthCodeNotFoundException(message: String) : HotSpotException(ErrorStatus.AUTH_CODE_NOT_FOUND, message)