package com.dol.domain.hotplace.presentation

import com.dol.domain.hotplace.presentation.data.request.CreateHotplaceRequest
import com.dol.domain.hotplace.presentation.data.request.QueryHotplaceListRequest
import com.dol.domain.hotplace.presentation.data.response.QueryHotplaceListResponse
import com.dol.domain.hotplace.presentation.data.response.QueryHotplaceResponse
import com.dol.domain.hotplace.service.CreateHotplaceService
import com.dol.domain.hotplace.service.QueryHotplaceListService
import com.dol.domain.hotplace.service.QueryHotplaceService
import com.dol.domain.hotplace.service.RecommendHotplaceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/hotplace")
class HotplaceController(
    private val createHotplaceService: CreateHotplaceService,
    private val queryHotplaceService: QueryHotplaceService,
    private val recommendHotplaceService: RecommendHotplaceService,
    private val queryHotplaceListService: QueryHotplaceListService
) {
    @PostMapping
    fun createHotplace(@RequestBody createHotplaceRequest: CreateHotplaceRequest): ResponseEntity<Void> {
        createHotplaceService.execute(createHotplaceRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/{idx}")
    fun queryHotplace(@PathVariable idx: UUID): ResponseEntity<QueryHotplaceResponse> {
        val response = queryHotplaceService.execute(idx)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @PostMapping("/recommend/{idx}")
    fun recommendHotplace(@PathVariable idx: UUID): ResponseEntity<Void> {
        recommendHotplaceService.execute(idx)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/list")
    fun queryHotplaceList(@RequestBody queryHotplaceListRequest: QueryHotplaceListRequest): ResponseEntity<List<QueryHotplaceListResponse>> {
        val response = queryHotplaceListService.execute(queryHotplaceListRequest)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}