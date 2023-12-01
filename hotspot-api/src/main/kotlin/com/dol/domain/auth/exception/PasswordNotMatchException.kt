package com.dol.domain.auth.exception

import com.dol.global.error.ErrorCode
import com.dol.global.error.exception.HotSpotException

class PasswordNotMatchException : HotSpotException(ErrorCode.PASSWORD_NOT_MATCH)