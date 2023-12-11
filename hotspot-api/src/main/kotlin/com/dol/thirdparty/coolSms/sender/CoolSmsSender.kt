package com.dol.thirdparty.coolSms.sender

import com.dol.thirdparty.coolSms.exception.MessageSendFailedException
import com.dol.thirdparty.coolSms.properties.CoolSmsProperties
import mu.KotlinLogging
import net.nurigo.java_sdk.api.Message
import net.nurigo.java_sdk.exceptions.CoolsmsException
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class CoolSmsSender(
    private val coolSmsProperties: CoolSmsProperties
) {

    fun sendSms(phoneNumber: String, authCode: Int) {
        val coolSms = Message(coolSmsProperties.access, coolSmsProperties.secret)

        val params: HashMap<String, String> = HashMap()
        params["to"] = phoneNumber
        params["from"] = coolSmsProperties.phoneNumber
        params["type"] = "SMS"
        params["text"] = "HotSpot 인증번호입니다. [ $authCode ]"

        try {
            coolSms.send(params)
        } catch (e: CoolsmsException) {
            log.error("coolsms message send failed")
            throw MessageSendFailedException("메시지 전송에 실패하였습니다.")
        }
    }

}