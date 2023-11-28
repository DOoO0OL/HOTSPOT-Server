package com.dol.global.security.jwt.common.exception

import com.dol.global.error.ErrorCode
import com.dol.global.error.exception.HotSpotException

class InvalidTokenTypeException : HotSpotException(ErrorCode.INVALID_TOKEN_TYPE)