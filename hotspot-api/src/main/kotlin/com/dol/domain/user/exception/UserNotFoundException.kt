package com.dol.domain.user.exception

import com.dol.global.error.ErrorCode
import com.dol.global.error.exception.HotSpotException

class UserNotFoundException : HotSpotException(ErrorCode.USER_NOT_FOUND)