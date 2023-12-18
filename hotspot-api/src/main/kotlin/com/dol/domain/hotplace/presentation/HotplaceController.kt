package com.dol.domain.hotplace.presentation

import com.dol.domain.hotplace.presentation.data.request.CreateHotplaceRequest
import com.dol.domain.hotplace.service.CreateHotplaceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("hotplace")
class HotplaceController(
    private val createHotplaceService: CreateHotplaceService
) {
    @PostMapping
    fun createHotplace(@RequestBody createHotplaceRequest: CreateHotplaceRequest): ResponseEntity<Void> {
        createHotplaceService.execute(createHotplaceRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}