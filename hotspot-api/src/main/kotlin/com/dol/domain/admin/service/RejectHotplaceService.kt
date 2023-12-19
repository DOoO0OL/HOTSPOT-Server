package com.dol.domain.admin.service

import java.util.UUID

interface RejectHotplaceService {
    fun execute(hotplaceIdx: UUID)
}