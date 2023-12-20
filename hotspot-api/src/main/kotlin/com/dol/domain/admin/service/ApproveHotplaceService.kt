package com.dol.domain.admin.service

import java.util.UUID

interface ApproveHotplaceService {
    fun execute(hotplaceIdx: UUID)
}