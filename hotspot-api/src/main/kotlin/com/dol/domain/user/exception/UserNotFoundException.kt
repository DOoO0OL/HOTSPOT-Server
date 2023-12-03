package com.dol.domain.user.exception

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException

class UserNotFoundException(message: String) : HotSpotException(ErrorStatus.USER_NOT_FOUND)