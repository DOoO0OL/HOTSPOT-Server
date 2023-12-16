package com.dol.thirdparty.aws.exception

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException

class FailedFileUploadException(message: String) : HotSpotException(ErrorStatus.FAILED_FILE_UPLOAD, message)