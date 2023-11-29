package com.dol.domain.auth.exception

import com.dol.global.error.ErrorCode
import com.dol.global.error.exception.HotSpotException

class DuplicateNickNameException : HotSpotException(ErrorCode.DUPLICATE_NICK_NAME)