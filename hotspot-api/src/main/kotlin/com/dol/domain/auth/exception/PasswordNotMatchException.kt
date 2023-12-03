package com.dol.domain.auth.exception

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException

class PasswordNotMatchException(message: String) : HotSpotException(ErrorStatus.PASSWORD_NOT_MATCH, message)