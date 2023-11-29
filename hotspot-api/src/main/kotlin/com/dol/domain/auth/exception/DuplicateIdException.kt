package com.dol.domain.auth.exception

import com.dol.global.error.ErrorCode
import com.dol.global.error.exception.HotSpotException

class DuplicateIdException : HotSpotException(ErrorCode.DUPLICATE_USER_ID)