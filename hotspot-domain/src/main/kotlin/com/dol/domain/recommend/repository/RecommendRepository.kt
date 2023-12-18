package com.dol.domain.recommend.repository

import com.dol.domain.hotplace.entity.Hotplace
import com.dol.domain.recommend.entity.Recommend
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RecommendRepository : CrudRepository<Recommend, UUID> {
    @Query("SELECT COUNT(*) FROM Recommend r WHERE r.hotplace = :hotplace")
    fun getHotplaceRecommendCount(hotplace: Hotplace): Long
}