package com.dol.global.error.exception

import com.dol.global.error.ErrorCode

open class HotSpotException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)