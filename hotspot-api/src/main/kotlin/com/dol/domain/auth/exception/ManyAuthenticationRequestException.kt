package com.dol.domain.auth.exception

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException

class ManyAuthenticationRequestException(message: String) : HotSpotException(ErrorStatus.MANY_AUTHENTICATION_REQUEST, message){
}