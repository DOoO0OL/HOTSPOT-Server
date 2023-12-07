package com.dol.domain.follow.exception

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException

class AlreadyFollowException(message: String) : HotSpotException(ErrorStatus.ALREADY_FOLLOW, message)