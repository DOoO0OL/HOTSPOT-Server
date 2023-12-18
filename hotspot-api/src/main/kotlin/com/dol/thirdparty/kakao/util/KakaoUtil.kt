package com.dol.thirdparty.kakao.util

import com.dol.thirdparty.kakao.properties.KakaoProperties
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.boot.json.JsonParser
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
    fun addressInCoordinate(address: String): Pair<Double, Double> {
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

        val jsonObject = JSONParser().parse(response.body) as JSONObject

        return (jsonObject["x"].toString().toDouble() to jsonObject["y"].toString().toDouble())
    }
}