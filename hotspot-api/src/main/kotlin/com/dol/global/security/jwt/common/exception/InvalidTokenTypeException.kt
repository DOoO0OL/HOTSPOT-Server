package com.dol.global.security.jwt.common.exception

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException

class InvalidTokenTypeException(message: String) : HotSpotException(ErrorStatus.INVALID_TOKEN_TYPE, message)