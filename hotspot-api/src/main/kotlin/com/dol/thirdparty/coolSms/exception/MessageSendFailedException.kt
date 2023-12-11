package com.dol.thirdparty.coolSms.exception

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException

class MessageSendFailedException(message: String) : HotSpotException(ErrorStatus.MESSAGE_SEND_FAILED, message)