package com.dol.domain.hotplace.repository

import com.dol.domain.hotplace.entity.Hotplace
import com.dol.domain.user.entity.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface HotplaceRepository : CrudRepository<Hotplace, UUID> {
    fun findAllByLatitudeBetweenAndLongitudeBetween(
        startLatitude: Double,
        endLatitude: Double,
        startLongitude: Double,
        endLongitude: Double
    ):List<Hotplace>

    @Query("SELECT h FROM Hotplace h JOIN Recommend r ON h = r.hotplace WHERE r.user = :user")
    fun queryAllRecommendedHotplace(user: User): List<Hotplace>
}