package com.dol.domain.auth.exception

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException

class DuplicateIdException(message: String) : HotSpotException(ErrorStatus.DUPLICATE_USER_ID, message)