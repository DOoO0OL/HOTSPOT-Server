package com.dol.domain.hotplace.repository

import com.dol.domain.hotplace.entity.Hotplace
import org.springframework.data.repository.CrudRepository

interface HotplaceRepository : CrudRepository<Hotplace, Long> {
}