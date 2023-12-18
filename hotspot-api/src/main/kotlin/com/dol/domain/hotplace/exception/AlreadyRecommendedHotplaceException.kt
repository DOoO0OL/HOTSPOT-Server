package com.dol.domain.hotplace.exception

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException

class AlreadyRecommendedHotplaceException(
    val msg: String
) : HotSpotException(ErrorStatus.ALREADY_RECOMMENDED_HOTPLACE, msg) {
}