package com.dol.global.error.exception

import com.dol.global.error.ErrorStatus


open class HotSpotException(val errorStatus: ErrorStatus, override val message: String) : RuntimeException(message)