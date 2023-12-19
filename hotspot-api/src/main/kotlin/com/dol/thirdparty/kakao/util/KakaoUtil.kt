package com.dol.thirdparty.kakao.util

import com.dol.thirdparty.kakao.properties.KakaoProperties
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


@Component
class KakaoUtil(
    private val kakaoProperties: KakaoProperties
) {
    private val logger = LoggerFactory.getLogger(KakaoUtil::class.java)
    fun addressInCoordinate(address: String): Pair<String, String> {
        val restTemplate = RestTemplate()

        val headers = org.springframework.http.HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.set("Authorization", "KakaoAK ${kakaoProperties.key}")
        val request: HttpEntity<String> = HttpEntity(headers)

        val uriComponents = UriComponentsBuilder
            .fromHttpUrl(kakaoProperties.url)
            .queryParam("query", address)
            .build()

        val response = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, request, String::class.java)

        val jsonObject = (JSONParser().parse(response.body) as JSONObject)
        val documents = JSONParser().parse(jsonObject["documents"].toString()) as JSONArray
        val coordinate = JSONParser().parse(documents[0].toString()) as JSONObject

        return coordinate["x"].toString() to coordinate["y"].toString()
    }
}