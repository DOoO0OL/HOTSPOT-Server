package com.dol.domain.admin.presentation

import com.dol.domain.admin.service.ApproveHotplaceService
import com.dol.domain.admin.service.RejectHotplaceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/admin")
class AdminController(
    private val approveHotplaceService: ApproveHotplaceService
    private val rejectHotplaceService: RejectHotplaceService
) {
    @PatchMapping("/approve/{hotplace_idx}")
    fun approveHotplace(@PathVariable("hotplace_idx") hotplaceIdx: UUID): ResponseEntity<Void> {
        approveHotplaceService.execute(hotplaceIdx)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PatchMapping("/reject/{hotplace_id}")
    fun rejectHotplace(@PathVariable("hotplace_id") hotplaceIdx: UUID): ResponseEntity<Void> {
        rejectHotplaceService.execute(hotplaceIdx)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}