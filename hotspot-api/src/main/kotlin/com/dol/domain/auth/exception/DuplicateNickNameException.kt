package com.dol.domain.auth.exception

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException

class DuplicateNickNameException(message: String) : HotSpotException(ErrorStatus.DUPLICATE_NICK_NAME)