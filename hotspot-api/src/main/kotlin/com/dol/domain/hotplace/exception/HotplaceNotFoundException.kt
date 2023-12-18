package com.dol.domain.hotplace.exception

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException

class HotplaceNotFoundException(
    val msg: String
) : HotSpotException(ErrorStatus.HOTPLACE_NOT_FOUND, msg) {
}