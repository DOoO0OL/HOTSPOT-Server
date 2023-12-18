package com.dol.domain.recommend.repository

import com.dol.domain.recommend.entity.Recommend
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RecommendRepository : CrudRepository<Recommend, UUID> {
}