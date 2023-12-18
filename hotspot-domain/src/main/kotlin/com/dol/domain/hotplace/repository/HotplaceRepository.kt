package com.dol.domain.hotplace.repository

import com.dol.domain.hotplace.entity.Hotplace
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface HotplaceRepository : CrudRepository<Hotplace, UUID> {
}