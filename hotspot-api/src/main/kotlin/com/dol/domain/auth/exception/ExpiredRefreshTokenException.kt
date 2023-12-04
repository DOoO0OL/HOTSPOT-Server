package com.dol.domain.auth.exception

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException

class ExpiredRefreshTokenException(message: String) : HotSpotException(ErrorStatus.EXPIRED_REFRESH_TOKEN, message)