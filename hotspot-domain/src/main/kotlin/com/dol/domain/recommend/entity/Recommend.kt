package com.dol.domain.recommend.entity

import com.dol.common.entity.BaseUUIDEntity
import com.dol.domain.hotplace.entity.Hotplace
import com.dol.domain.user.entity.User
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class Recommend(
    @Column(name = "idx", columnDefinition = "BINARY(16)", nullable = false)
    override val idx: UUID,

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    val user: User,

    @ManyToOne
    @JoinColumn(name = "hotplace_idx", nullable = false)
    val hotplace: Hotplace,

    @CreatedDate
    val createdAt: LocalDateTime
) : BaseUUIDEntity(idx)